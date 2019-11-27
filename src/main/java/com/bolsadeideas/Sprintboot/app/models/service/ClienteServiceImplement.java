package com.bolsadeideas.Sprintboot.app.models.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.Sprintboot.app.models.dao.IClienteDao;
import com.bolsadeideas.Sprintboot.app.models.entity.Cliente;



@Service
public class ClienteServiceImplement implements IClienteService {
	private final static Logger LOGGER = Logger.getLogger("myLogger");
	
	@Autowired
	private IClienteDao clienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findall() {

		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);

	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
	 LOGGER.info("Inicio Cliente findOne");
	 try {
		if (id==null) {
	          LOGGER.info("el cliente no fue encontrado, id: "+id); 
	        }
	 LOGGER.info("id Cliente: "+id);
	 LOGGER.info("Fin Cliente findOne");
	 }
	 catch(Exception e) {
		 LOGGER.info("Error");
	 }
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);

	}

	@Override
	@Transactional
	public Cliente findClienteByName(String name) {
		// TODO Auto-generated method stub

		return null;
	}

}
