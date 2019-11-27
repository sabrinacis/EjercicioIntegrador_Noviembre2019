package com.bolsadeideas.Sprintboot.app.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.NumberFormat;




@Entity
@Table(name = "facturas")
public class Factura implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private static final long serialVersionUID = 1l;

	@NotEmpty
	private String descripcion;
	
	@NotEmpty
	private String nombreCliente;

	@NotNull
	@NumberFormat
    private Integer totalFactura;
	
	@NotNull
	@NumberFormat
	private Integer productoId;
    
	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

 
	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Integer getTotalFactura() {
		return totalFactura;
	}
	

	public void setTotalFactura(Integer totalFactura) {
		this.totalFactura = totalFactura;
	}
	
	
	
	

}
