
package com.softtek.acceleo.demo.wizard.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softtek.acceleo.demo.wizard.domain.Pedido;
import com.softtek.acceleo.demo.wizard.repository.PedidoRepository;

@Repository("pedidoRepository")
public class PedidoRepositoryImpl implements PedidoRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void addPedido(Pedido pedido) {
		sessionFactory.getCurrentSession().persist(pedido);
	}

	public void editPedido(Pedido pedido) {
		sessionFactory.getCurrentSession().update(pedido);

	}

	@SuppressWarnings({ "unchecked" })
	public List<Pedido> listPedidos(Pedido pedido) {

		if (pedido != null) {

			Pedido pedidoProxy = new Pedido();








			return (List<Pedido>) sessionFactory.getCurrentSession()
					.createCriteria(Pedido.class)
					.add(Example.create(pedidoProxy)).list();

		}

		return (List<Pedido>) sessionFactory.getCurrentSession()
				.createCriteria(Pedido.class).list();

	}


	@SuppressWarnings("unchecked")
	public List<Pedido> listPedidosQuery(Pedido pedido, String query, int page, int size) {
			//pedidoProxy.set#columnsGrid(pedido.get#columnsGrid());
			return (List<Pedido>) sessionFactory.getCurrentSession()
					.createCriteria(Pedido.class).setFirstResult((page - 1) * size)
					.add(	
							Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(	
						Restrictions.like("almacen", "%" + query +"%"),Restrictions.like("fecha", "%" + query +"%")),Restrictions.like("estatus", "%" + query +"%")),Restrictions.like("cliente_id", "%" + query +"%")),Restrictions.like("cliente", "%" + query +"%")),Restrictions.like("numero", "%" + query +"%"))	
	
	
	
	
	
).list();
	}


	@SuppressWarnings("unchecked")
	public List<Pedido> listPedidosPagination(Pedido pedido, int page, int size) {
			//cuentaProxy.set#columnsGrid(cuenta.get#columnsGrid());
			return (List<Pedido>) sessionFactory.getCurrentSession()
				.createCriteria(Pedido.class).setFirstResult((page - 1) * size)
				.setMaxResults(size).list();
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRows() {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Pedido.class).setProjection(Projections.rowCount())
		.uniqueResult();	
		return totalRows;  
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRowsSearch(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Pedido.class).setProjection(Projections.rowCount())
					.add(	
							Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(	
						Restrictions.like("almacen", "%" + query +"%"),Restrictions.like("fecha", "%" + query +"%")),Restrictions.like("estatus", "%" + query +"%")),Restrictions.like("cliente_id", "%" + query +"%")),Restrictions.like("cliente", "%" + query +"%")),Restrictions.like("numero", "%" + query +"%"))	
	
	
	
	
	
).uniqueResult();
		return totalRows;  
	}


	@SuppressWarnings({ "unchecked" })
	public long getTotalRows(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Pedido.class).setProjection(Projections.rowCount())
		
		.uniqueResult();
		return totalRows;  
	}

	

	public Pedido getPedido(int empid) {
		return (Pedido) sessionFactory.getCurrentSession().get(
				Pedido.class, empid);
	}

	public void deletePedido(Pedido pedido) {
		sessionFactory.getCurrentSession().delete(pedido);
	}

}
