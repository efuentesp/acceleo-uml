
package com.softtek.acceleo.demo.wizard.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softtek.acceleo.demo.wizard.domain.Genero;
import com.softtek.acceleo.demo.wizard.repository.GeneroRepository;

@Repository("generoRepository")
public class GeneroRepositoryImpl implements GeneroRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void addGenero(Genero genero) {
		sessionFactory.getCurrentSession().persist(genero);
	}

	public void editGenero(Genero genero) {
		sessionFactory.getCurrentSession().update(genero);

	}

	@SuppressWarnings({ "unchecked" })
	public List<Genero> listGeneros(Genero genero) {

		if (genero != null) {

			Genero generoProxy = new Genero();




			return (List<Genero>) sessionFactory.getCurrentSession()
					.createCriteria(Genero.class)
					.add(Example.create(generoProxy)).list();

		}

		return (List<Genero>) sessionFactory.getCurrentSession()
				.createCriteria(Genero.class).list();

	}


	@SuppressWarnings("unchecked")
	public List<Genero> listGenerosQuery(Genero genero, String query, int page, int size) {
			//generoProxy.set#columnsGrid(genero.get#columnsGrid());
			return (List<Genero>) sessionFactory.getCurrentSession()
					.createCriteria(Genero.class).setFirstResult((page - 1) * size)
					.add(	
							Restrictions.or(	
						Restrictions.like("valor", "%" + query +"%"),Restrictions.like("nombre", "%" + query +"%"))	
	
).list();
	}


	@SuppressWarnings("unchecked")
	public List<Genero> listGenerosPagination(Genero genero, int page, int size) {
			//cuentaProxy.set#columnsGrid(cuenta.get#columnsGrid());
			return (List<Genero>) sessionFactory.getCurrentSession()
				.createCriteria(Genero.class).setFirstResult((page - 1) * size)
				.setMaxResults(size).list();
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRows() {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Genero.class).setProjection(Projections.rowCount())
		.uniqueResult();	
		return totalRows;  
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRowsSearch(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Genero.class).setProjection(Projections.rowCount())
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
		.createCriteria(Genero.class).setProjection(Projections.rowCount())
		
		.uniqueResult();
		return totalRows;  
	}

	

	public Genero getGenero(int empid) {
		return (Genero) sessionFactory.getCurrentSession().get(
				Genero.class, empid);
	}

	public void deleteGenero(Genero genero) {
		sessionFactory.getCurrentSession().delete(genero);
	}

}
