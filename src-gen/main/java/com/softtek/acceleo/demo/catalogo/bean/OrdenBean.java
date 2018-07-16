package com.softtek.acceleo.demo.catalogo.bean;

import java.util.Date;

public class OrdenBean {

	private Integer id;

	private String fecha;
	private String cliente_id;
	private String numero;
	private String estatus;
	private String almacen;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFecha () {
	    return fecha;  		
    }

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCliente_id () {
	    return cliente_id;  		
    }

	public void setCliente_id(String cliente_id) {
		this.cliente_id = cliente_id;
	}
	public String getNumero () {
	    return numero;  		
    }

	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getEstatus () {
	    return estatus;  		
    }

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getAlmacen () {
	    return almacen;  		
    }

	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}
}
