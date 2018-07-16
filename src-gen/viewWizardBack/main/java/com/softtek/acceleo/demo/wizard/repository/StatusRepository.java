
package com.softtek.acceleo.demo.wizard.repository;

import java.util.List;
import com.softtek.acceleo.demo.wizard.domain.Status;

public interface StatusRepository {

	
	 public void addStatus(Status status);   
	 
	 public void editStatus(Status status);
	   
	 public List<Status> listStatuss(Status status);   
	    
	 public Status getStatus(int empid);   
	    
	 public void deleteStatus(Status status); 

	 public List<Status> listStatussQuery(Status status, String query, int page, int size);

	 public List<Status> listStatussPagination(Status status, int page, int size);	

     public long getTotalRows();

     public long getTotalRows(String query);

     public long getTotalRowsSearch(String query);

}

