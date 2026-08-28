package com.example.demo.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CacheLruService {

	private final Map<String, String> cache;
	@Value("${cache.capacity}")
	private int capacity;
	public CacheLruService() {
		this.cache=new LinkedHashMap<>();
	}
	public synchronized String get(String key) {
		return cache.get(key);
	}
	
	public synchronized void put(String key,String value) {
		if(cache.containsKey(key)) {
			cache.put(key, value);
			return  ;
		}
		if(cache.size()>=capacity) {
			String X=cache.keySet().iterator().next();
			cache.remove(X);
		}
		cache.put(key, value);
	}
	public synchronized void delete(String key) {
		cache.remove(key);
	}
	public synchronized void clear() {
		cache.clear();
	}
	
}
