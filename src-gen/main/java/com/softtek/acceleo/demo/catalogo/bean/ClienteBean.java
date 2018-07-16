package com.softtek.acceleo.demo.catalogo.bean;

import java.util.Date;

public class ClienteBean {

	private Integer id;

	private String numero;
	private String nombre;
	private String rfc;
	private String direccion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero () {
	    return numero;  		
    }

	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNombre () {
	    return nombre;  		
    }

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRfc () {
	    return rfc;  		
    }

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getDireccion () {
	    return direccion;  		
    }

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
