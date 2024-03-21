package com.crud.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crud.app.services.SeatMenu;

public class App 
{

	public static final Logger logger = LoggerFactory.getLogger(App.class);
	
    public static void main(String[] args)
    {
    	logger.info(SeatMenu.findById(2));
    	logger.info("application is starting");
    }
    
}
