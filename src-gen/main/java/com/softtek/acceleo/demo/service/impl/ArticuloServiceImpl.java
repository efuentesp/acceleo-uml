

package com.softtek.acceleo.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.acceleo.demo.repository.ArticuloRepository;
import com.softtek.acceleo.demo.domain.Articulo;
import com.softtek.acceleo.demo.service.ArticuloService;

@Service("articuloService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ArticuloServiceImpl implements ArticuloService {

	@Autowired
	private ArticuloRepository articuloRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addArticulo(Articulo articulo) {
		
		articuloRepository.addArticulo(articulo);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editArticulo(Articulo articulo) {
		
		articuloRepository.editArticulo(articulo);
	}
	
	
	public List<Articulo> listArticuloss(Articulo articulo) {

		return articuloRepository.listArticuloss(articulo);
	}

	public Articulo getArticulo(int empid) {

		return articuloRepository.getArticulo(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteArticulo(Articulo articulo) {
		System.out.println("Entrando al deleteArticulo");

		 articuloRepository.deleteArticulo(articulo);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Articulo> listArticulosPagination(Articulo articulo, int page, int size) {

		return articuloRepository.listArticulosPagination(articulo, page, size);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRowsSearch(String query) {

		return articuloRepository.getTotalRowsSearch(query);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows(String query) {

		return articuloRepository.getTotalRows(query);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows() {

		return articuloRepository.getTotalRows();
	}

	


	public List<Articulo> listArticulossQuery(Articulo articulo, String query, int page, int size) {
		// TODO Auto-generated method stub
		return articuloRepository.listArticulossQuery(articulo, query, page, size);
	}


	

	

}

