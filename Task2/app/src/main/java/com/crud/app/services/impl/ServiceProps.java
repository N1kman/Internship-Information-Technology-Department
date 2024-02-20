package com.crud.app.services.impl;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ServiceProps {
	String filename;
	
	public ServiceProps(String filename) {
		this.filename = filename;
	}
	
	public PropertiesConfiguration loadProps() {
		PropertiesConfiguration props = null;
		try {
			props = new PropertiesConfiguration(filename);
		}  catch(ConfigurationException exception) {
			System.err.println(exception.getMessage());
		}
		return props;	
	}
}
