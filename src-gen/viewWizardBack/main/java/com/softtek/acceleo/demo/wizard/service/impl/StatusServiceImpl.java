

package com.softtek.acceleo.demo.wizard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.acceleo.demo.wizard.repository.StatusRepository;
import com.softtek.acceleo.demo.wizard.domain.Status;
import com.softtek.acceleo.demo.wizard.service.StatusService;

@Service("statusService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addStatus(Status status) {
		
		statusRepository.addStatus(status);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editStatus(Status status) {
		
		statusRepository.editStatus(status);
	}
	
	
	public List<Status> listStatuss(Status status) {

		return statusRepository.listStatuss(status);
	}

	public Status getStatus(int empid) {

		return statusRepository.getStatus(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteStatus(Status status) {
		System.out.println("Entrando al deleteStatus");

		 statusRepository.deleteStatus(status);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Status> listStatussPagination(Status status, int page, int size) {

		return statusRepository.listStatussPagination(status, page, size);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRowsSearch(String query) {

		return statusRepository.getTotalRowsSearch(query);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows(String query) {

		return statusRepository.getTotalRows(query);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows() {

		return statusRepository.getTotalRows();
	}

	public List<Status> listStatussQuery(Status status, String query, int page, int size) {
		// TODO Auto-generated method stub
		return statusRepository.listStatussQuery(status, query, page, size);
	}

}

