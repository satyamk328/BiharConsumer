package com.digital.service;

import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.ccavenue.security.AesCryptUtil;
import com.digital.spring.model.CCavenue;

@Service
public class ObjectCache {

	@Value("${accessCode}")
	private String accessCode;
	
	@Value("${workingkey}")
	private String workingKey;
	
	@CacheEvict(value = { "cmsCache", "languageLookup", "appLocalizationCache" }, allEntries = true)
	public void clearAppCache() {
	}

	public CCavenue encript(String data) {
		AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
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
		Map<String, String> hs = new Hashtable<String, String>();
		String pair = null, pname = null, pvalue = null;
		while (tokenizer.hasMoreTokens()) {
			pair = (String) tokenizer.nextToken();
			if (pair != null) {
				StringTokenizer strTok = new StringTokenizer(pair, "=");
				pname = "";
				pvalue = "";
				if (strTok.hasMoreTokens()) {
					pname = (String) strTok.nextToken();
					if (strTok.hasMoreTokens())
						pvalue = (String) strTok.nextToken();
					hs.put(pname, pvalue);
				}
			}
		}
		return hs;
	}
}
