package com.crud.app.services.impl;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceProps {
	
	public static final Logger logger = LoggerFactory.getLogger(ServiceProps.class.getName());

	public static PropertiesConfiguration loadProps(String filename) {
		PropertiesConfiguration props = null;
		try {
			logger.trace("Creates and loads the extended properties from \"{}\"", filename);
			props = new PropertiesConfiguration(filename);
		}  catch(ConfigurationException exception) {
			logger.error("Problems reading the extended properties from \"{}\"", filename);
		}
		return props;	
	}
}
