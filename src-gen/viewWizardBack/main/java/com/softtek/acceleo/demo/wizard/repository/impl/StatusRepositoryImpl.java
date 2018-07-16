
package com.softtek.acceleo.demo.wizard.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softtek.acceleo.demo.wizard.domain.Status;
import com.softtek.acceleo.demo.wizard.repository.StatusRepository;

@Repository("statusRepository")
public class StatusRepositoryImpl implements StatusRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void addStatus(Status status) {
		sessionFactory.getCurrentSession().persist(status);
	}

	public void editStatus(Status status) {
		sessionFactory.getCurrentSession().update(status);

	}

	@SuppressWarnings({ "unchecked" })
	public List<Status> listStatuss(Status status) {

		if (status != null) {

			Status statusProxy = new Status();




			return (List<Status>) sessionFactory.getCurrentSession()
					.createCriteria(Status.class)
					.add(Example.create(statusProxy)).list();

		}

		return (List<Status>) sessionFactory.getCurrentSession()
				.createCriteria(Status.class).list();

	}


	@SuppressWarnings("unchecked")
	public List<Status> listStatussQuery(Status status, String query, int page, int size) {
			//statusProxy.set#columnsGrid(status.get#columnsGrid());
			return (List<Status>) sessionFactory.getCurrentSession()
					.createCriteria(Status.class).setFirstResult((page - 1) * size)
					.add(	
							Restrictions.or(	
						Restrictions.like("valor", "%" + query +"%"),Restrictions.like("nombre", "%" + query +"%"))	
	
).list();
	}


	@SuppressWarnings("unchecked")
	public List<Status> listStatussPagination(Status status, int page, int size) {
			//cuentaProxy.set#columnsGrid(cuenta.get#columnsGrid());
			return (List<Status>) sessionFactory.getCurrentSession()
				.createCriteria(Status.class).setFirstResult((page - 1) * size)
				.setMaxResults(size).list();
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRows() {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Status.class).setProjection(Projections.rowCount())
		.uniqueResult();	
		return totalRows;  
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRowsSearch(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Status.class).setProjection(Projections.rowCount())
					.add(	
							Restrictions.or(	
						Restrictions.like("valor", "%" + query +"%"),Restrictions.like("nombre", "%" + query +"%"))	
	
).uniqueResult();
		return totalRows;  
	}


	@SuppressWarnings({ "unchecked" })
	public long getTotalRows(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Status.class).setProjection(Projections.rowCount())
		
		.uniqueResult();
		return totalRows;  
	}

	

	public Status getStatus(int empid) {
		return (Status) sessionFactory.getCurrentSession().get(
				Status.class, empid);
	}

	public void deleteStatus(Status status) {
		sessionFactory.getCurrentSession().delete(status);
	}

}
