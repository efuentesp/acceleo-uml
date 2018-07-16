package com.softtek.acceleo.demo.wizard.service;

import com.softtek.acceleo.demo.wizard.domain.Estado;
import java.util.List;

public interface EstadoService {

	public void addEstado(Estado estado);

	public void editEstado(Estado estado);
	
	public List<Estado> listEstados(Estado estado);

	public Estado getEstado(int empid);

	public void deleteEstado(Estado estado);
	
	public List<Estado> listEstadosQuery(Estado estado, String query, int page, int size);

	public List<Estado> listEstadosPagination(Estado estado, int page, int size);
	

	public long getTotalRows();

	public long getTotalRows(String query);

	public long getTotalRowsSearch(String query);

}

