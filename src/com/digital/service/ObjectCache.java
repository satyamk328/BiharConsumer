package com.digital.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.ccavenue.security.AesCryptUtil;
import com.digital.spring.model.CCavenue;

@Service
public class ObjectCache {

	@CacheEvict(value = { "cmsCache", "languageLookup", "appLocalizationCache" }, allEntries = true)
	public void clearAppCache() {
	}

	public CCavenue getValues(HttpServletRequest request) {
		String accessCode = ""; // Put in the Access Code in quotes provided by CCAVENUES.
		String workingKey = ""; // Put in the 32 Bit Working Key provided by CCAVENUES.
		Enumeration<String> enumeration = request.getParameterNames();
		String ccaRequest = "", pname = "", pvalue = "";
		while (enumeration.hasMoreElements()) {
			pname = "" + enumeration.nextElement();
			pvalue = request.getParameter(pname);
			ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
		}
		AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
		String encRequest = aesUtil.encrypt(ccaRequest);
		CCavenue cavenue = new CCavenue();
		cavenue.setAccessCode(accessCode);
		cavenue.setEncrypted(encRequest);
		cavenue.setWorkingKey(workingKey);
		return cavenue;
	}
}
