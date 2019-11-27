

package com.bolsadeideas.Sprintboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.Sprintboot.app.models.entity.Producto;


public interface IProductoDao extends CrudRepository<Producto, Long>{
	

}
