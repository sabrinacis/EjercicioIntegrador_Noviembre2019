package com.bolsadeideas.Sprintboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolsadeideas.Sprintboot.app.models.dao.IProductoDao;
import com.bolsadeideas.Sprintboot.app.models.entity.Producto;

@Service
public class ProductoServiceImplement implements IProductoService {

	@Autowired
	private IProductoDao productoDao;
		
	@Override
	@Transactional(readOnly=true)
	public List<Producto> findall() {
		
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Producto producto) {
		productoDao.save(producto);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findOne(Long id) {
		
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productoDao.deleteById(id);
		
	}

}
