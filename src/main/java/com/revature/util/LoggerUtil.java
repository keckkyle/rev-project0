package com.revature.util;

import org.apache.log4j.Logger;

public class LoggerUtil {
	
	private static Logger log = Logger.getRootLogger();
	
	public void fatal(String m) {
		log.fatal(m);
	}
	public void error(String m) {
		log.error(m);
	}
	public void warn(String m) {
		log.warn(m);
	}
	public void debug(String m) {
		log.debug(m);
	}
	public void info(String m) {
		log.info(m);
	}
	public void trace(String m) {
		log.trace(m);
	}

}