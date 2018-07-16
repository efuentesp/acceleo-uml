
package com.softtek.acceleo.demo.wizard.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Pedido")
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

    @Column(name = "almacen") 
	private String almacen;
    @Column(name = "fecha") 
	private String fecha;
    @Column(name = "estatus") 
	private String estatus;
    @Column(name = "cliente_id") 
	private String cliente_id;
    @Column(name = "numero") 
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
