
package com.softtek.acceleo.demo.repository;

import java.util.List;
import com.softtek.acceleo.demo.domain.Articulo;

public interface ArticuloRepository {

	
	 public void addArticulo(Articulo articulo);   
	 
	 public void editArticulo(Articulo articulo);
	   
	 public List<Articulo> listArticuloss(Articulo articulo);   
	    
	 public Articulo getArticulo(int empid);   
	    
	 public void deleteArticulo(Articulo articulo); 

	 public List<Articulo> listArticulossQuery(Articulo articulo, String query, int page, int size);

	 public List<Articulo> listArticulosPagination(Articulo articulo, int page, int size);	

     public long getTotalRows();

     public long getTotalRows(String query);

     public long getTotalRowsSearch(String query);

	

 			

	
}

