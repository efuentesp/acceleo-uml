
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
@Table(name = "Cliente")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

    @Column(name = "numero") 
	private String numero;
    @Column(name = "nombre") 
	private String nombre;
    @Column(name = "rfc") 
	private String rfc;
    @Column(name = "direccion") 
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
