
package com.softtek.acceleo.demo.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softtek.acceleo.demo.domain.Agencia;
import com.softtek.acceleo.demo.repository.AgenciaRepository;

@Repository("agenciaRepository")
public class AgenciaRepositoryImpl implements AgenciaRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void addAgencia(Agencia agencia) {
		sessionFactory.getCurrentSession().persist(agencia);
	}

	public void editAgencia(Agencia agencia) {
		sessionFactory.getCurrentSession().update(agencia);

	}

	@SuppressWarnings({ "unchecked" })
	public List<Agencia> listAgenciass(Agencia agencia) {

		if (agencia != null) {

			Agencia agenciaProxy = new Agencia();




			return (List<Agencia>) sessionFactory.getCurrentSession()
					.createCriteria(Agencia.class)
					.add(Example.create(agenciaProxy)).list();

		}

		return (List<Agencia>) sessionFactory.getCurrentSession()
				.createCriteria(Agencia.class).list();

	}


	@SuppressWarnings("unchecked")
	public List<Agencia> listAgenciassQuery(Agencia agencia, String query, int page, int size) {
			//agenciaProxy.set#columnsGrid(agencia.get#columnsGrid());
			return (List<Agencia>) sessionFactory.getCurrentSession()
					.createCriteria(Agencia.class).setFirstResult((page - 1) * size)
					.add(	
							Restrictions.or(	
						Restrictions.like("nombre", "%" + query +"%"),Restrictions.like("pais", "%" + query +"%"))	
	
).list();
	}


	@SuppressWarnings("unchecked")
	public List<Agencia> listAgenciasPagination(Agencia agencia, int page, int size) {
			//cuentaProxy.set#columnsGrid(cuenta.get#columnsGrid());
			return (List<Agencia>) sessionFactory.getCurrentSession()
				.createCriteria(Agencia.class).setFirstResult((page - 1) * size)
				
				.setMaxResults(size).list();
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRows() {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Agencia.class).setProjection(Projections.rowCount())
		.uniqueResult();	
		return totalRows;  
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRowsSearch(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Agencia.class).setProjection(Projections.rowCount())
					.add(	
							Restrictions.or(	
						Restrictions.like("nombre", "%" + query +"%"),Restrictions.like("pais", "%" + query +"%"))	
	
).uniqueResult();
		return totalRows;  
	}


	@SuppressWarnings({ "unchecked" })
	public long getTotalRows(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Agencia.class).setProjection(Projections.rowCount())
		
		.uniqueResult();
		return totalRows;  
	}

	

	public Agencia getAgencia(int empid) {
		return (Agencia) sessionFactory.getCurrentSession().get(
				Agencia.class, empid);
	}

	public void deleteAgencia(Agencia agencia) {
		sessionFactory.getCurrentSession().delete(agencia);
	}


	
	
	
}
