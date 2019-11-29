package com.bolsadeideas.Sprintboot.app.models.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.bolsadeideas.Sprintboot.app.models.entity.Cliente;


public interface IClienteService {
	
    public List<Cliente>findall();
	
	public void save(Cliente cliente);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);
	
	public Cliente findClienteByName(String name);
	
	//public List<Cliente> findClientByName(@Param("name") String name);
}
