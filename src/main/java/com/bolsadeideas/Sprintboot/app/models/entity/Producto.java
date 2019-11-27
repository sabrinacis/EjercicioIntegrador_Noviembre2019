package com.bolsadeideas.Sprintboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.NumberFormat;

import com.sun.istack.NotNull;


@Entity
public class Producto  implements Serializable{
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private static final long serialVersionUID = 6218863442531760470L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long  id;
	
	@NotEmpty
	private String nombre;
	
	@NotNull
	@NumberFormat
	private Integer precio;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
