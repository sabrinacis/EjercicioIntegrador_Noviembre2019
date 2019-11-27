package com.bolsadeideas.Sprintboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bolsadeideas.Sprintboot.app.models.dao.IDetalleFacturaDao;
import com.bolsadeideas.Sprintboot.app.models.entity.DetalleFactura;


@Service
public class DetalleFacturaServiceImplement implements IDetalleFacturaService {

	@Autowired
	private IDetalleFacturaDao detalleFacturaDao;

	@Override
	public List<DetalleFactura> findall() {
		return (List<DetalleFactura>) detalleFacturaDao.findAll();
	}


	@Override
	public void delete(Long id) {
		detalleFacturaDao.deleteById(id);
		
	}

	@Override
	public Integer totalPrecio(Integer cantidad, Integer precio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(DetalleFactura detalleFactura) {
		detalleFacturaDao.save(detalleFactura);
		
	}

	@Override
	public DetalleFactura findOne(Long id) {
		return detalleFacturaDao.findById(id).orElse(null);
	}
			

}
