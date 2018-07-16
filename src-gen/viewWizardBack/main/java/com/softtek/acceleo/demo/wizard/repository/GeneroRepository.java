
package com.softtek.acceleo.demo.wizard.repository;

import java.util.List;
import com.softtek.acceleo.demo.wizard.domain.Genero;

public interface GeneroRepository {

	
	 public void addGenero(Genero genero);   
	 
	 public void editGenero(Genero genero);
	   
	 public List<Genero> listGeneros(Genero genero);   
	    
	 public Genero getGenero(int empid);   
	    
	 public void deleteGenero(Genero genero); 

	 public List<Genero> listGenerosQuery(Genero genero, String query, int page, int size);

	 public List<Genero> listGenerosPagination(Genero genero, int page, int size);	

     public long getTotalRows();

     public long getTotalRows(String query);

     public long getTotalRowsSearch(String query);

}

