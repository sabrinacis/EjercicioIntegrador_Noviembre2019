

package com.bolsadeideas.Sprintboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bolsadeideas.Sprintboot.app.models.entity.Cliente;


public interface IClienteDao extends CrudRepository<Cliente, Long>{
	/*
	 * 
	 * @Query("SELECT c FROM clientes c WHERE (c.nombre = :name)") public
	 * List<Cliente> findClientByName(@Param("name") String name);
	 */
}
