package com.digital.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class ObjectCache {

	@CacheEvict(value = { "cmsCache", "languageLookup", "appLocalizationCache" }, allEntries = true)
	public void clearAppCache() {
	}

}
