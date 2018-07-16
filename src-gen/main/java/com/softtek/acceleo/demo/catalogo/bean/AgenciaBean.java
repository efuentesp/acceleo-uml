package com.softtek.acceleo.demo.catalogo.bean;

import java.util.Date;

public class AgenciaBean {

	private Integer id;

	private String nombre;
	private String pais;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre () {
	    return nombre;  		
    }

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPais () {
	    return pais;  		
    }

	public void setPais(String pais) {
		this.pais = pais;
	}
}
