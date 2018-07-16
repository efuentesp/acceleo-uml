
package com.softtek.acceleo.demo.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softtek.acceleo.demo.domain.Orden;
import com.softtek.acceleo.demo.repository.OrdenRepository;

@Repository("ordenRepository")
public class OrdenRepositoryImpl implements OrdenRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void addOrden(Orden orden) {
		sessionFactory.getCurrentSession().persist(orden);
	}

	public void editOrden(Orden orden) {
		sessionFactory.getCurrentSession().update(orden);

	}

	@SuppressWarnings({ "unchecked" })
	public List<Orden> listOrdenss(Orden orden) {

		if (orden != null) {

			Orden ordenProxy = new Orden();







			return (List<Orden>) sessionFactory.getCurrentSession()
					.createCriteria(Orden.class)
					.add(Example.create(ordenProxy)).list();

		}

		return (List<Orden>) sessionFactory.getCurrentSession()
				.createCriteria(Orden.class).list();

	}


	@SuppressWarnings("unchecked")
	public List<Orden> listOrdenssQuery(Orden orden, String query, int page, int size) {
			//ordenProxy.set#columnsGrid(orden.get#columnsGrid());
			return (List<Orden>) sessionFactory.getCurrentSession()
					.createCriteria(Orden.class).setFirstResult((page - 1) * size)
					.add(	
							Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(	
						Restrictions.like("fecha", "%" + query +"%"),Restrictions.like("cliente_id", "%" + query +"%")),Restrictions.like("numero", "%" + query +"%")),Restrictions.like("estatus", "%" + query +"%")),Restrictions.like("almacen", "%" + query +"%"))	
	
	
	
	
).list();
	}


	@SuppressWarnings("unchecked")
	public List<Orden> listOrdensPagination(Orden orden, int page, int size, String strCliente
	) {
			//cuentaProxy.set#columnsGrid(cuenta.get#columnsGrid());
			return (List<Orden>) sessionFactory.getCurrentSession()
				.createCriteria(Orden.class).setFirstResult((page - 1) * size)
				.add(Restrictions.eq("cliente_id", strCliente) )
				
				.setMaxResults(size).list();
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRows() {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Orden.class).setProjection(Projections.rowCount())
		.uniqueResult();	
		return totalRows;  
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRowsSearch(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Orden.class).setProjection(Projections.rowCount())
					.add(	
							Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(	
						Restrictions.like("fecha", "%" + query +"%"),Restrictions.like("cliente_id", "%" + query +"%")),Restrictions.like("numero", "%" + query +"%")),Restrictions.like("estatus", "%" + query +"%")),Restrictions.like("almacen", "%" + query +"%"))	
	
	
	
	
).uniqueResult();
		return totalRows;  
	}


	@SuppressWarnings({ "unchecked" })
	public long getTotalRows(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Orden.class).setProjection(Projections.rowCount())
		.add(Restrictions.eq("cliente_id", query) )
		
		.uniqueResult();
		return totalRows;  
	}

		@SuppressWarnings({ "unchecked" })
		public long getTotalRows(String strCliente, String query) {
			
			long totalRows = 0;
			totalRows = (Long) sessionFactory.getCurrentSession()
			.createCriteria(Orden.class).setProjection(Projections.rowCount())
			.add(Restrictions.eq("cliente_id", strCliente) )
						.add(	
								Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(	
							Restrictions.like("fecha", "%" + query +"%"),Restrictions.like("cliente_id", "%" + query +"%")),Restrictions.like("numero", "%" + query +"%")),Restrictions.like("estatus", "%" + query +"%")),Restrictions.like("almacen", "%" + query +"%"))	
		
		
		
		
	).uniqueResult();
			
			return totalRows;  
	
		}
	

	public Orden getOrden(int empid) {
		return (Orden) sessionFactory.getCurrentSession().get(
				Orden.class, empid);
	}

	public void deleteOrden(Orden orden) {
		sessionFactory.getCurrentSession().delete(orden);
	}


	@SuppressWarnings("unchecked")
		public List<Orden> listOrdensFilterMaster(Orden orden, String cliente, int page, int size) {
				//facturaProxy.set#columnsGrid(factura.get#columnsGrid());
				return (List<Orden>) sessionFactory.getCurrentSession()
						.createCriteria(Orden.class).setFirstResult((page - 1) * size)
						.add(Restrictions.eq("cliente_id", cliente) ).list();
		}
	
	
	@SuppressWarnings("unchecked")
		public List<Orden> listOrdenssQueryByCliente(Orden orden, String query, String cliente, int page, int size) {
				//ordenProxy.set#columnsGrid(orden.get#columnsGrid());
				return (List<Orden>) sessionFactory.getCurrentSession()
						.createCriteria(Orden.class).setFirstResult((page - 1) * size)
						.add(Restrictions.eq("cliente_id", cliente) )
						.add(	
								Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(	
							Restrictions.like("fecha", "%" + query +"%"),Restrictions.like("cliente_id", "%" + query +"%")),Restrictions.like("numero", "%" + query +"%")),Restrictions.like("estatus", "%" + query +"%")),Restrictions.like("almacen", "%" + query +"%"))	
		
		
		
		
	).list();
		}
	
}
