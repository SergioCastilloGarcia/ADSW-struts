package com.miw.presentation;

import javax.inject.Named;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.apache.logging.log4j.*;

@Named ("counter")
@ApplicationScoped
public class CounterBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6563343002612103664L;
	
	Logger logger = LogManager.getLogger(this.getClass());
	
	public CounterBean() {
		logger.debug("Initializing counter");
	}
	Integer count = 0 ;
	public Integer inc()
	{
		return ++ count;
	}
	public Integer getCount() {
		
		return count;
	}
}
