package com.softtek.acceleo.demo.wizard.service;

import com.softtek.acceleo.demo.wizard.domain.Cliente;
import java.util.List;

public interface ClienteService {

	public void addCliente(Cliente cliente);

	public void editCliente(Cliente cliente);
	
	public List<Cliente> listClientes(Cliente cliente);

	public Cliente getCliente(int empid);

	public void deleteCliente(Cliente cliente);
	
	public List<Cliente> listClientesQuery(Cliente cliente, String query, int page, int size);

	public List<Cliente> listClientesPagination(Cliente cliente, int page, int size);
	

	public long getTotalRows();

	public long getTotalRows(String query);

	public long getTotalRowsSearch(String query);

}

