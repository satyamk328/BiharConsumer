package com.digital.service;

import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.digital.enums.Payment;
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

	@CacheEvict(value = { "userDetails.userId", "userAllData", "topAllCity", "cityDetails.CityName",
			"cityDetails.CityId", "allAmenity", "amenityDetails.Id", "routesDetails",
			"walletHistory" }, allEntries = true)
	public void clearAppCache() {
	}

	public CCavenue encript(String data) {
		AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
		data = data.replace("${MERCHANTID}", merchantId);
		data = data.replace("${REDIRECT_URL}", redirectUrl);
		data = data.replace("${CANCEL_URL}", cancelRedirectUrl);
		String encRequest = aesUtil.encrypt(data);
		CCavenue cavenue = new CCavenue();
		cavenue.setAccessCode(accessCode);
		cavenue.setEncrypted(encRequest);
		return cavenue;
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
		if (StringUtils.isBlank(hs.get("order_status"))) {
			hs.put("order_message", "");
		} else {
			hs.put("order_message", Payment.getPaymentMessage(Payment.valueOf(hs.get("order_status").toUpperCase())));
		}
		return hs;
	}
	
}
