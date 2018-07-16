
package com.softtek.acceleo.demo.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softtek.acceleo.demo.domain.Articulo;
import com.softtek.acceleo.demo.repository.ArticuloRepository;

@Repository("articuloRepository")
public class ArticuloRepositoryImpl implements ArticuloRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void addArticulo(Articulo articulo) {
		sessionFactory.getCurrentSession().persist(articulo);
	}

	public void editArticulo(Articulo articulo) {
		sessionFactory.getCurrentSession().update(articulo);

	}

	@SuppressWarnings({ "unchecked" })
	public List<Articulo> listArticuloss(Articulo articulo) {

		if (articulo != null) {

			Articulo articuloProxy = new Articulo();






			return (List<Articulo>) sessionFactory.getCurrentSession()
					.createCriteria(Articulo.class)
					.add(Example.create(articuloProxy)).list();

		}

		return (List<Articulo>) sessionFactory.getCurrentSession()
				.createCriteria(Articulo.class).list();

	}


	@SuppressWarnings("unchecked")
	public List<Articulo> listArticulossQuery(Articulo articulo, String query, int page, int size) {
			//articuloProxy.set#columnsGrid(articulo.get#columnsGrid());
			return (List<Articulo>) sessionFactory.getCurrentSession()
					.createCriteria(Articulo.class).setFirstResult((page - 1) * size)
					.add(	
							Restrictions.or(Restrictions.or(Restrictions.or(	
						Restrictions.like("categoria", "%" + query +"%"),Restrictions.like("precio", "%" + query +"%")),Restrictions.like("cantidad", "%" + query +"%")),Restrictions.like("descripcion", "%" + query +"%"))	
	
	
	
).list();
	}


	@SuppressWarnings("unchecked")
	public List<Articulo> listArticulosPagination(Articulo articulo, int page, int size) {
			//cuentaProxy.set#columnsGrid(cuenta.get#columnsGrid());
			return (List<Articulo>) sessionFactory.getCurrentSession()
				.createCriteria(Articulo.class).setFirstResult((page - 1) * size)
				
				.setMaxResults(size).list();
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRows() {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Articulo.class).setProjection(Projections.rowCount())
		.uniqueResult();	
		return totalRows;  
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRowsSearch(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Articulo.class).setProjection(Projections.rowCount())
					.add(	
							Restrictions.or(Restrictions.or(Restrictions.or(	
						Restrictions.like("categoria", "%" + query +"%"),Restrictions.like("precio", "%" + query +"%")),Restrictions.like("cantidad", "%" + query +"%")),Restrictions.like("descripcion", "%" + query +"%"))	
	
	
	
).uniqueResult();
		return totalRows;  
	}


	@SuppressWarnings({ "unchecked" })
	public long getTotalRows(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Articulo.class).setProjection(Projections.rowCount())
		
		.uniqueResult();
		return totalRows;  
	}

	

	public Articulo getArticulo(int empid) {
		return (Articulo) sessionFactory.getCurrentSession().get(
				Articulo.class, empid);
	}

	public void deleteArticulo(Articulo articulo) {
		sessionFactory.getCurrentSession().delete(articulo);
	}


	
	
	
}
