

package com.softtek.acceleo.demo.wizard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.acceleo.demo.wizard.repository.EstadoRepository;
import com.softtek.acceleo.demo.wizard.domain.Estado;
import com.softtek.acceleo.demo.wizard.service.EstadoService;

@Service("estadoService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addEstado(Estado estado) {
		
		estadoRepository.addEstado(estado);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editEstado(Estado estado) {
		
		estadoRepository.editEstado(estado);
	}
	
	
	public List<Estado> listEstados(Estado estado) {

		return estadoRepository.listEstados(estado);
	}

	public Estado getEstado(int empid) {

		return estadoRepository.getEstado(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteEstado(Estado estado) {
		System.out.println("Entrando al deleteEstado");

		 estadoRepository.deleteEstado(estado);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Estado> listEstadosPagination(Estado estado, int page, int size) {

		return estadoRepository.listEstadosPagination(estado, page, size);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRowsSearch(String query) {

		return estadoRepository.getTotalRowsSearch(query);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows(String query) {

		return estadoRepository.getTotalRows(query);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows() {

		return estadoRepository.getTotalRows();
	}

	public List<Estado> listEstadosQuery(Estado estado, String query, int page, int size) {
		// TODO Auto-generated method stub
		return estadoRepository.listEstadosQuery(estado, query, page, size);
	}

}

