
package com.softtek.acceleo.demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Articulo")
public class Articulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

    @Column(name = "categoria") 
	private String categoria;
    @Column(name = "precio") 
	private String precio;
    @Column(name = "cantidad") 
	private String cantidad;
    @Column(name = "descripcion") 
	private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoria () {
	    return categoria;  		
    }

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getPrecio () {
	    return precio;  		
    }

	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getCantidad () {
	    return cantidad;  		
    }

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion () {
	    return descripcion;  		
    }

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}			
