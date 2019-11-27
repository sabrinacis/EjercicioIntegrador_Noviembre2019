package com.bolsadeideas.Sprintboot.app.models.service;

import java.util.List;
import com.bolsadeideas.Sprintboot.app.models.entity.DetalleFactura;



public interface IDetalleFacturaService {
	
	public List<DetalleFactura>findall();
	
	public void save(DetalleFactura detalleFactura);
	
	public DetalleFactura findOne(Long id);
	
	public void delete(Long id);
	
	public Integer totalPrecio(Integer cantidad,Integer precio);
}
