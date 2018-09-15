package com.jmx.cache.names.MemoryCacheNames;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApp {
	private static Logger LOGGER = null;

	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
		LOGGER = Logger.getLogger(MainApp.class.getName());
	}
	
	public static void main(String args[]) throws InterruptedException {

		NameManagement nameMgmt = new NameManagement();
		
		LOGGER.log(Level.INFO, "");
		LOGGER.log(Level.INFO, "------ Validate encryption ------");
		LOGGER.log(Level.INFO, "result = " + nameMgmt.validateEncryption("Joshep") + "\n");
		LOGGER.log(Level.INFO, "result = " + nameMgmt.validateEncryption("Sridhar") + "\n");
		LOGGER.log(Level.INFO, "result = " + nameMgmt.validateEncryption("Sridhar") + "\n");
		LOGGER.log(Level.INFO, "result = " + nameMgmt.validateEncryption("Amin") + "\n");
		
		LOGGER.log(Level.INFO, "------ Validate decryption ------");
		LOGGER.log(Level.INFO, "result = " + nameMgmt.validateDecryption("inAm") + "\n");
		LOGGER.log(Level.INFO, "result = " + nameMgmt.validateDecryption("ilAn") + "\n");
		LOGGER.log(Level.INFO, "result = " + nameMgmt.validateDecryption("ilAn") + "\n");
	}
}
