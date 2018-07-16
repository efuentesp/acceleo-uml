
package com.softtek.acceleo.demo.repository;

import java.util.List;
import com.softtek.acceleo.demo.domain.Agencia;

public interface AgenciaRepository {

	
	 public void addAgencia(Agencia agencia);   
	 
	 public void editAgencia(Agencia agencia);
	   
	 public List<Agencia> listAgenciass(Agencia agencia);   
	    
	 public Agencia getAgencia(int empid);   
	    
	 public void deleteAgencia(Agencia agencia); 

	 public List<Agencia> listAgenciassQuery(Agencia agencia, String query, int page, int size);

	 public List<Agencia> listAgenciasPagination(Agencia agencia, int page, int size);	

     public long getTotalRows();

     public long getTotalRows(String query);

     public long getTotalRowsSearch(String query);

	

 			

	
}

