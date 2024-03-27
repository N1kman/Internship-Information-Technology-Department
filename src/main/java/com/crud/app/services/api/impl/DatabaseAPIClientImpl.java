package com.crud.app.services.api.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crud.app.entities.Client;
import com.crud.app.exceptions.Messages;
import com.crud.app.services.api.DatabaseAPIClient;
import com.crud.app.services.dao.DAO;
import com.crud.app.services.dao.impl.ClientDAOImpl;

public class DatabaseAPIClientImpl implements DatabaseAPIClient {

	public static final Logger logger = LoggerFactory.getLogger(DatabaseAPIClientImpl.class);
	
	@Override
	public Client findById(long id) {
		Client client = null;
		try {
		    DAO<Client> service = new ClientDAOImpl();
		    client = service.findById(id);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
		}
    	return client;
	}

	@Override
	public List<Client> findAll() {
		List<Client> clients = null;
		try {
		    DAO<Client> service = new ClientDAOImpl();
		    clients = service.findAll();
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
		}
    	return clients;
	}

	@Override
	public Client insert(Client template) {
		Client client = null;
		try {
		    DAO<Client> service = new ClientDAOImpl();
		    client = service.insert(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
		}
    	return client;
	}

	@Override
	public Client update(Client template) {
		Client client = null;
		try {
		    DAO<Client> service = new ClientDAOImpl();
		    client = service.update(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
		}
    	return client;
	}

	@Override
	public Client delete(Client template) {
		Client client = null;
		try {
		    DAO<Client> service = new ClientDAOImpl();
		    client = service.delete(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
		}
    	return client;
	}

}
