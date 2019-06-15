package com.digital.service;

import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

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

	@CacheEvict(value = { "userDetails.userId", "userAllData", "topAllCity", "cityDetails.CityName",
			"cityDetails.CityId", "allAmenity", "amenityDetails.Id", "routesDetails",
			"walletHistory" }, allEntries = true)
	public void clearAppCache() {
	}

	public CCavenue encript(String data) {
		AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
		String reData = data.replace("${MERCHANTID}", merchantId);
		String encRequest = aesUtil.encrypt(reData);
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
		return hs;
	}
}
