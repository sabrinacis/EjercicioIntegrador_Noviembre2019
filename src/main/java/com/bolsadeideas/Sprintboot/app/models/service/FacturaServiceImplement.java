package com.bolsadeideas.Sprintboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.Sprintboot.app.models.dao.IFacturaDao;
import com.bolsadeideas.Sprintboot.app.models.entity.Factura;
import java.util.logging.Logger;


@Service
public class FacturaServiceImplement implements IFacturaService{

	private final static Logger LOGGER = Logger.getLogger("myLogger");
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Factura> findall() {
		return (List<Factura>) facturaDao.findAll();
	}

	@Override
	@Transactional
	public void save(Factura factura) {	  
		
		LOGGER.info("comienzo save factura");
		try {
			
			facturaDao.save(factura);
		}
	
	  catch (Exception e) {
	        e.getStackTrace();
	    }   
		LOGGER.info("fin save factura");
	}
	
	

	@Override
	@Transactional(readOnly=true)
	public Factura findOne(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		facturaDao.deleteById(id);
		
	}

	@Override
	public Integer totalPrecio(Integer cantidad, Integer precio) {
		Integer total=precio*cantidad;		
		return total;
	}
	

}
