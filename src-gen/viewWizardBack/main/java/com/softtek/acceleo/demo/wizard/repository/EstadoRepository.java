
package com.softtek.acceleo.demo.wizard.repository;

import java.util.List;
import com.softtek.acceleo.demo.wizard.domain.Estado;

public interface EstadoRepository {

	
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

