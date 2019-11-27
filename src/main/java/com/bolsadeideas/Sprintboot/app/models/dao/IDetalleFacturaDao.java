

package com.bolsadeideas.Sprintboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.Sprintboot.app.models.entity.Cliente;
import com.bolsadeideas.Sprintboot.app.models.entity.DetalleFactura;


public interface IDetalleFacturaDao extends CrudRepository<DetalleFactura, Long>{
	

}
