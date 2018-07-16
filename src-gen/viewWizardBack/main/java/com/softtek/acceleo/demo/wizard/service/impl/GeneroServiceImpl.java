

package com.softtek.acceleo.demo.wizard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.acceleo.demo.wizard.repository.GeneroRepository;
import com.softtek.acceleo.demo.wizard.domain.Genero;
import com.softtek.acceleo.demo.wizard.service.GeneroService;

@Service("generoService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GeneroServiceImpl implements GeneroService {

	@Autowired
	private GeneroRepository generoRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addGenero(Genero genero) {
		
		generoRepository.addGenero(genero);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editGenero(Genero genero) {
		
		generoRepository.editGenero(genero);
	}
	
	
	public List<Genero> listGeneros(Genero genero) {

		return generoRepository.listGeneros(genero);
	}

	public Genero getGenero(int empid) {

		return generoRepository.getGenero(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteGenero(Genero genero) {
		System.out.println("Entrando al deleteGenero");

		 generoRepository.deleteGenero(genero);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Genero> listGenerosPagination(Genero genero, int page, int size) {

		return generoRepository.listGenerosPagination(genero, page, size);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRowsSearch(String query) {

		return generoRepository.getTotalRowsSearch(query);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows(String query) {

		return generoRepository.getTotalRows(query);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows() {

		return generoRepository.getTotalRows();
	}

	public List<Genero> listGenerosQuery(Genero genero, String query, int page, int size) {
		// TODO Auto-generated method stub
		return generoRepository.listGenerosQuery(genero, query, page, size);
	}

}

