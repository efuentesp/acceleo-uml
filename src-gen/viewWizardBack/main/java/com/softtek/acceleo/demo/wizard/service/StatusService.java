package com.softtek.acceleo.demo.wizard.service;

import com.softtek.acceleo.demo.wizard.domain.Status;
import java.util.List;

public interface StatusService {

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

