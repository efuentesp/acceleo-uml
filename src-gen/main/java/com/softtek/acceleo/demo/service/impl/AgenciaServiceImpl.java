

package com.softtek.acceleo.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.acceleo.demo.repository.AgenciaRepository;
import com.softtek.acceleo.demo.domain.Agencia;
import com.softtek.acceleo.demo.service.AgenciaService;

@Service("agenciaService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AgenciaServiceImpl implements AgenciaService {

	@Autowired
	private AgenciaRepository agenciaRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addAgencia(Agencia agencia) {
		
		agenciaRepository.addAgencia(agencia);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editAgencia(Agencia agencia) {
		
		agenciaRepository.editAgencia(agencia);
	}
	
	
	public List<Agencia> listAgenciass(Agencia agencia) {

		return agenciaRepository.listAgenciass(agencia);
	}

	public Agencia getAgencia(int empid) {

		return agenciaRepository.getAgencia(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteAgencia(Agencia agencia) {
		System.out.println("Entrando al deleteAgencia");

		 agenciaRepository.deleteAgencia(agencia);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Agencia> listAgenciasPagination(Agencia agencia, int page, int size) {

		return agenciaRepository.listAgenciasPagination(agencia, page, size);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRowsSearch(String query) {

		return agenciaRepository.getTotalRowsSearch(query);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows(String query) {

		return agenciaRepository.getTotalRows(query);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows() {

		return agenciaRepository.getTotalRows();
	}

	


	public List<Agencia> listAgenciassQuery(Agencia agencia, String query, int page, int size) {
		// TODO Auto-generated method stub
		return agenciaRepository.listAgenciassQuery(agencia, query, page, size);
	}


	

	

}

