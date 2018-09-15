package com.jmx.cache.names.MemoryCacheNames;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class NameManagementTest {
    
    @Test
    public void testLoadPredefinedNamesInCache() {
    	NameManagement nm = new NameManagement();
    	assertNotNull(nm.nameCache);
    	assertEquals(nm.nameCache.size(),5);
    }
    
    @Test
    public void testValidateEncryptionFail() {
    	NameManagement nm = new NameManagement();
    	assertNull(nm.validateEncryption(null));
    	assertNull(nm.validateEncryption(""));
    }
    
    @Test
    public void testValidateEncryption() {
    	NameManagement nm = new NameManagement();	
    	assertNotNull(nm.validateEncryption("Sridhar"));
    	assertEquals(nm.validateEncryption("Sridhar"),"har#Srid");
    	assertNotNull(nm.validateEncryption("Amin"));
    	assertEquals(nm.validateEncryption("Amin"),"inAm");
    	assertNotNull(nm.validateEncryption("Jesus"));
    	assertEquals(nm.validateEncryption("Jesus"),"us#Jes");
    }   
	
    @Test
    public void testValidateDecryptionFail() {
    	NameManagement nm = new NameManagement();
    	assertNull(nm.validateDecryption(null));
    	assertNull(nm.validateDecryption(""));
    }
    
    @Test
    public void testValidateDecryption() {
    	NameManagement nm = new NameManagement();
    	assertNotNull(nm.validateDecryption("har#Srid"));
    	assertEquals(nm.validateDecryption("har#Srid"),"Sridhar");
    	assertNotNull(nm.validateDecryption("inAm"));
    	assertEquals(nm.validateDecryption("inAm"),"Amin");
    	assertNotNull(nm.validateDecryption("us#Jes"));
    	assertEquals(nm.validateDecryption("us#Jes"),"Jesus");
    }   
	    
    @Test
    public void testAddNameToCache() throws ExecutionException {
    	NameManagement nm = new NameManagement();
    	nm.validateEncryption("Sridhar");
    	assertNotNull(nm.nameCache.get("har#Srid"));
    	assertEquals(nm.nameCache.get("har#Srid"),"har#Srid");
    }
    
}
