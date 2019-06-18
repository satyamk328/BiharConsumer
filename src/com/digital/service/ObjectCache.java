package com.digital.service;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.digital.audit.dao.ServiceApiResponseLogDao;
import com.digital.audit.model.ServiceApiAuditLog;
import com.digital.enums.AppName;
import com.digital.spring.model.CCavenue;
import com.digital.utils.AesCryptUtil;

@Service
public class ObjectCache {

	@Value("${accessCode}")
	private String accessCode;

	@Value("${workingkey}")
	private String workingKey;

	@Value("${merchantId}")
	private String merchantId;

	@Value("${redirectURL}")
	private String redirectUrl;

	@Value("${cancelURL}")
	private String cancelRedirectUrl;

	@Autowired
	private ServiceApiResponseLogDao responseLog;

	@CacheEvict(value = { "userDetails.UserId", "userAllData", "topAllCity", "cityDetails.CityName",
			"cityDetails.CityId", "allAmenity", "amenityDetails.Id", "routesDetails",
			"walletHistory" }, allEntries = true)
	public void clearAppCache() {
	}

	public CCavenue encript(String data, HttpServletRequest request) {
		AesCryptUtil aesUtil = new AesCryptUtil(workingKey);

		final ServiceApiAuditLog restLog = new ServiceApiAuditLog();
		restLog.setApiName(AppName.CCAVENUE_PAYMENT_GATE_WAY.name());
		restLog.setChannelType("Web");
		restLog.setStartTime(new Date());
		restLog.setRequestBody(data);
		restLog.setRequestHeader(getRequestHeaders(request));
		restLog.setRequestUrl("https://secure.ccavenue.com/transaction/transaction.do?command=initiateTransaction");
		restLog.setRequestHttpMethod(getRequestType(request));
		Long id = responseLog.addLog(restLog);

		data = data.replace("${MERCHANT_PARAM}", String.valueOf(id));
		data = data.replace("${MERCHANTID}", merchantId);
		data = data.replace("${REDIRECT_URL}", redirectUrl);
		data = data.replace("${CANCEL_URL}", cancelRedirectUrl);
		String encRequest = aesUtil.encrypt(data);
		CCavenue cavenue = new CCavenue();
		cavenue.setAccessCode(accessCode);
		cavenue.setEncrypted(encRequest);
		return cavenue;
	}

	private String getRequestType(final HttpServletRequest request) {
		if (request != null && request.getMethod() != null) {
			return request.getMethod().toString();
		} else {
			return null;
		}
	}

	private String getRequestHeaders(final HttpServletRequest request) {
		if (request != null && request.getHeaderNames() != null) {
			Map<String, String> map = new HashMap<String, String>();
			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				String value = request.getHeader(key);
				map.put(key, value);
			}
			return map.toString();
		} else {
			return null;
		}
	}

	public Map<String, String> decript(String encriptData) {
		AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
		String decResp = aesUtil.decrypt(encriptData);
		StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
		Map<String, String> hs = new Hashtable<>();
		String pair = null;
		while (tokenizer.hasMoreTokens()) {
			pair = (String) tokenizer.nextToken();
			if (pair != null) {
				StringTokenizer strTok = new StringTokenizer(pair, "=");
				String pname = "";
				String pvalue = "";
				if (strTok.hasMoreTokens()) {
					pname = (String) strTok.nextToken();
					if (strTok.hasMoreTokens())
						pvalue = (String) strTok.nextToken();
					hs.put(pname, pvalue);// order_status
				}
			}
		}
		Long logId = Long.valueOf(hs.get("merchant_param1"));
		ServiceApiAuditLog apiAuditLog = responseLog.getLog(logId);
		apiAuditLog.setEndTime(new Date());
		apiAuditLog.setDurationMilliSeconds(apiAuditLog.getEndTime().getTime() - apiAuditLog.getStartTime().getTime());
		apiAuditLog.setResponseBody(hs.toString());
		apiAuditLog.setResponseHeader("");
		apiAuditLog.setResponseHttpCode(hs.get("order_status").equalsIgnoreCase("Success") ? 200 : 400);
		responseLog.updateLog(apiAuditLog);
		return hs;
	}

}
