
package com.softtek.acceleo.demo.repository;

import java.util.List;
import com.softtek.acceleo.demo.domain.Orden;

public interface OrdenRepository {

	
	 public void addOrden(Orden orden);   
	 
	 public void editOrden(Orden orden);
	   
	 public List<Orden> listOrdenss(Orden orden);   
	    
	 public Orden getOrden(int empid);   
	    
	 public void deleteOrden(Orden orden); 

	 public List<Orden> listOrdenssQuery(Orden orden, String query, int page, int size);

	 public List<Orden> listOrdensPagination(Orden orden, int page, int size, String strCliente
	 );	

     public long getTotalRows();

     public long getTotalRows(String query);

     public long getTotalRowsSearch(String query);

	public long getTotalRows(String strCliente, String query);
	

 	public List<Orden> listOrdensFilterMaster(Orden orden, String cliente, int page, int size);
 			

	public List<Orden> listOrdenssQueryByCliente(Orden orden, String query, String cliente, int page, int size);
	
}

