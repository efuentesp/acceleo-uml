

package com.softtek.acceleo.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.acceleo.demo.repository.OrdenRepository;
import com.softtek.acceleo.demo.domain.Orden;
import com.softtek.acceleo.demo.service.OrdenService;

@Service("ordenService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrdenServiceImpl implements OrdenService {

	@Autowired
	private OrdenRepository ordenRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addOrden(Orden orden) {
		
		ordenRepository.addOrden(orden);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editOrden(Orden orden) {
		
		ordenRepository.editOrden(orden);
	}
	
	
	public List<Orden> listOrdenss(Orden orden) {

		return ordenRepository.listOrdenss(orden);
	}

	public Orden getOrden(int empid) {

		return ordenRepository.getOrden(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteOrden(Orden orden) {
		System.out.println("Entrando al deleteOrden");

		 ordenRepository.deleteOrden(orden);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Orden> listOrdensPagination(Orden orden, int page, int size, String strCliente
	) {

		return ordenRepository.listOrdensPagination(orden, page, size, strCliente
		);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRowsSearch(String query) {

		return ordenRepository.getTotalRowsSearch(query);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows(String query) {

		return ordenRepository.getTotalRows(query);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows() {

		return ordenRepository.getTotalRows();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
		public long getTotalRows(String strCliente, String query) {
			return ordenRepository.getTotalRows(strCliente, query);
		}
	


	public List<Orden> listOrdenssQuery(Orden orden, String query, int page, int size) {
		// TODO Auto-generated method stub
		return ordenRepository.listOrdenssQuery(orden, query, page, size);
	}


	public List<Orden> listOrdensFilterMaster(Orden orden, String cliente, int page, int size) {
			// TODO Auto-generated method stub
			return ordenRepository.listOrdensFilterMaster(orden, cliente, page, size);
	}
	

	public List<Orden> listOrdenssQueryByCliente(Orden orden, String query, String cliente, int _page, int size) {
			// TODO Auto-generated method stub
			return ordenRepository.listOrdenssQueryByCliente(orden, query, cliente, _page, size);
	}
	

}

