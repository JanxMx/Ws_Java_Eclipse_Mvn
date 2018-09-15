package com.jmx.cache.names.MemoryCacheNames;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class NameManagement {
	Logger logger = Logger.getLogger(NameManagement.class.getName());
	
	LoadingCache<String, String> nameCache;

	public NameManagement() {
		createMapInCache();
		loadPredefinedNamesInCache();
	}
	
	/**
	 * create a cache map for encrypted names
	 */
	private void createMapInCache() {
		this.nameCache = CacheBuilder
				.newBuilder()
				.expireAfterAccess(10, TimeUnit.MINUTES)
				.build(new CacheLoader<String, String>() { 

			@Override
			public String load(String name) throws Exception {
				
				logger.log(Level.INFO, "added to cache");
				
				return name;
			}
		});
	}
	
	/**
	 * Load some predefined names to Cache Memory
	 */
	private void loadPredefinedNamesInCache() {
		logger.log(Level.INFO, "------ Load five names to Cache ------");
		addNameToCache(encryptName("Alexander"));
		addNameToCache(encryptName("Adriana"));
		addNameToCache(encryptName("Michael"));
		addNameToCache(encryptName("Maria"));
		addNameToCache(encryptName("Peter"));
	}
	
	/**
	 * Validation of the encryption process
	 * Verify that the encrypted names be in the cache
	 * @param name
	 * @return encrypted name
	 */
	public String validateEncryption(String name) {		
		
		if(name==null || name.isEmpty()) {
			logger.log(Level.SEVERE, "Name is empty, try again!!");
			return null;
		}		
		
		String encryptedName = encryptName(name);
		
		if (nameCache.getIfPresent(encryptedName) == null) {
			return addNameToCache(encryptedName);
		}

		logger.log(Level.INFO, name + " found in cache");

		return encryptedName;
	}
		
	/**
	 * Validation of the decryption process
	 * Verify that the decrypted name be in the cache
	 * @param encryptedName
	 * @return decrypted name
	 */
	public String validateDecryption(String encryptedName) {
		
		if(encryptedName==null || encryptedName.isEmpty()) {
			logger.info("Name is empty, try again!!");
			return null;
		}	
		
		String decryptName = decryptName(encryptedName);
		
		if (nameCache.getIfPresent(encryptedName) == null) {
			addNameToCache(encryptedName);
		} else {
			logger.log(Level.INFO, encryptedName + " found in cache");
		}
			
		return decryptName;
	}	
	
	/**
	 * Add a name to the cache (as long as it does not exist)
	 * @param encryptedName
	 * @return
	 */
	private String addNameToCache(String encryptedName) {
		try {
			return nameCache.get(encryptedName);

		} catch (ExecutionException e) {
			logger.log(Level.SEVERE, "Error trying to get the value: " + encryptedName , e);
		}
		
		return null;
	}

	
	/**
	 * Encrypt the received name
	 * @param name
	 * @return encrypted name
	 */
	private String encryptName(String name) {

		if(name.isEmpty()) {
			logger.log(Level.SEVERE, "Name is empty, try again!!");
			return null;
		}
		
		String newName = ((name.length() % 2) > 0) ? name.concat("#") : name;
		
		int midLen = newName.length() / 2;
		String firstHalf = newName.substring(0, midLen);
		String secondHalf = newName.substring(midLen, newName.length());

		logger.log(Level.INFO, name + " encrypted");
		
		return secondHalf + firstHalf;
	}
	
	/**
	 * Decrypt the received name
	 * @param encryptedName
	 * @return decrypted name
	 */
	private String decryptName(String encryptedName) {
		
		if(encryptedName.isEmpty()) {
			logger.log(Level.SEVERE, "Name is empty, try again!!");
			return null;
		}
		
		logger.log(Level.INFO, "decrypting " + encryptedName);
		
		int midLen = encryptedName.length() / 2;
		String firstHalf = encryptedName.substring(0, midLen).replace("#","");
		String secondHalf = encryptedName.substring(midLen, encryptedName.length());
		
		return secondHalf + firstHalf;
	}		
	
}
