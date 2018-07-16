package com.softtek.acceleo.demo.wizard.catalogo.bean;

import java.util.Date;

public class PedidoBean {

	private Integer id;

	private String almacen;
	private String fecha;
	private String estatus;
	private String cliente_id;
	private String numero;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlmacen () {
	    return almacen;  		
    }

	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}
	public String getFecha () {
	    return fecha;  		
    }

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getEstatus () {
	    return estatus;  		
    }

	public void setEstatus(String estatus) {
		this.estatus = estatus;
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
}
