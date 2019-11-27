package com.bolsadeideas.Sprintboot.app.models.service;

import java.util.List;

import com.bolsadeideas.Sprintboot.app.models.entity.Cliente;
import com.bolsadeideas.Sprintboot.app.models.entity.DetalleFactura;
import com.bolsadeideas.Sprintboot.app.models.entity.Producto;


public interface IProductoService {
	
    public List<Producto>findall();
	
	public void save(Producto producto);
	
	public Producto findOne(Long id);
	
	public void delete(Long id);

}
