

package com.softtek.acceleo.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.acceleo.demo.repository.ClienteRepository;
import com.softtek.acceleo.demo.domain.Cliente;
import com.softtek.acceleo.demo.service.ClienteService;

@Service("clienteService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addCliente(Cliente cliente) {
		
		clienteRepository.addCliente(cliente);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editCliente(Cliente cliente) {
		
		clienteRepository.editCliente(cliente);
	}
	
	
	public List<Cliente> listClientess(Cliente cliente) {

		return clienteRepository.listClientess(cliente);
	}

	public Cliente getCliente(int empid) {

		return clienteRepository.getCliente(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteCliente(Cliente cliente) {
		System.out.println("Entrando al deleteCliente");

		 clienteRepository.deleteCliente(cliente);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Cliente> listClientesPagination(Cliente cliente, int page, int size) {

		return clienteRepository.listClientesPagination(cliente, page, size);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRowsSearch(String query) {

		return clienteRepository.getTotalRowsSearch(query);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows(String query) {

		return clienteRepository.getTotalRows(query);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows() {

		return clienteRepository.getTotalRows();
	}

	


	public List<Cliente> listClientessQuery(Cliente cliente, String query, int page, int size) {
		// TODO Auto-generated method stub
		return clienteRepository.listClientessQuery(cliente, query, page, size);
	}


	

	

}

