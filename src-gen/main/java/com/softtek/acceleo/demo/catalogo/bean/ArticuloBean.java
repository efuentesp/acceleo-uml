package com.softtek.acceleo.demo.catalogo.bean;

import java.util.Date;

public class ArticuloBean {

	private Integer id;

	private String categoria;
	private String precio;
	private String cantidad;
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
