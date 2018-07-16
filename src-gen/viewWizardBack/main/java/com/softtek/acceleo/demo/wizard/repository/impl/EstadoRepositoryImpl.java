
package com.softtek.acceleo.demo.wizard.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softtek.acceleo.demo.wizard.domain.Estado;
import com.softtek.acceleo.demo.wizard.repository.EstadoRepository;

@Repository("estadoRepository")
public class EstadoRepositoryImpl implements EstadoRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void addEstado(Estado estado) {
		sessionFactory.getCurrentSession().persist(estado);
	}

	public void editEstado(Estado estado) {
		sessionFactory.getCurrentSession().update(estado);

	}

	@SuppressWarnings({ "unchecked" })
	public List<Estado> listEstados(Estado estado) {

		if (estado != null) {

			Estado estadoProxy = new Estado();




			return (List<Estado>) sessionFactory.getCurrentSession()
					.createCriteria(Estado.class)
					.add(Example.create(estadoProxy)).list();

		}

		return (List<Estado>) sessionFactory.getCurrentSession()
				.createCriteria(Estado.class).list();

	}


	@SuppressWarnings("unchecked")
	public List<Estado> listEstadosQuery(Estado estado, String query, int page, int size) {
			//estadoProxy.set#columnsGrid(estado.get#columnsGrid());
			return (List<Estado>) sessionFactory.getCurrentSession()
					.createCriteria(Estado.class).setFirstResult((page - 1) * size)
					.add(	
							Restrictions.or(	
						Restrictions.like("valor", "%" + query +"%"),Restrictions.like("nombre", "%" + query +"%"))	
	
).list();
	}


	@SuppressWarnings("unchecked")
	public List<Estado> listEstadosPagination(Estado estado, int page, int size) {
			//cuentaProxy.set#columnsGrid(cuenta.get#columnsGrid());
			return (List<Estado>) sessionFactory.getCurrentSession()
				.createCriteria(Estado.class).setFirstResult((page - 1) * size)
				.setMaxResults(size).list();
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRows() {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Estado.class).setProjection(Projections.rowCount())
		.uniqueResult();	
		return totalRows;  
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRowsSearch(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Estado.class).setProjection(Projections.rowCount())
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
		.createCriteria(Estado.class).setProjection(Projections.rowCount())
		
		.uniqueResult();
		return totalRows;  
	}

	

	public Estado getEstado(int empid) {
		return (Estado) sessionFactory.getCurrentSession().get(
				Estado.class, empid);
	}

	public void deleteEstado(Estado estado) {
		sessionFactory.getCurrentSession().delete(estado);
	}

}
