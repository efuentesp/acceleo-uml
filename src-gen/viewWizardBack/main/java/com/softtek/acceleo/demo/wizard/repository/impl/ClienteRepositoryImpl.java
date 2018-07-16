
package com.softtek.acceleo.demo.wizard.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softtek.acceleo.demo.wizard.domain.Cliente;
import com.softtek.acceleo.demo.wizard.repository.ClienteRepository;

@Repository("clienteRepository")
public class ClienteRepositoryImpl implements ClienteRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void addCliente(Cliente cliente) {
		sessionFactory.getCurrentSession().persist(cliente);
	}

	public void editCliente(Cliente cliente) {
		sessionFactory.getCurrentSession().update(cliente);

	}

	@SuppressWarnings({ "unchecked" })
	public List<Cliente> listClientes(Cliente cliente) {

		if (cliente != null) {

			Cliente clienteProxy = new Cliente();











			return (List<Cliente>) sessionFactory.getCurrentSession()
					.createCriteria(Cliente.class)
					.add(Example.create(clienteProxy)).list();

		}

		return (List<Cliente>) sessionFactory.getCurrentSession()
				.createCriteria(Cliente.class).list();

	}


	@SuppressWarnings("unchecked")
	public List<Cliente> listClientesQuery(Cliente cliente, String query, int page, int size) {
			//clienteProxy.set#columnsGrid(cliente.get#columnsGrid());
			return (List<Cliente>) sessionFactory.getCurrentSession()
					.createCriteria(Cliente.class).setFirstResult((page - 1) * size)
					.add(	
							Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(	
						Restrictions.like("direccion", "%" + query +"%"),Restrictions.like("modalcliente", "%" + query +"%")),Restrictions.like("genero", "%" + query +"%")),Restrictions.like("nombre", "%" + query +"%")),Restrictions.like("rfc", "%" + query +"%")),Restrictions.like("gridcliente", "%" + query +"%")),Restrictions.like("numero", "%" + query +"%")),Restrictions.like("estado", "%" + query +"%")),Restrictions.like("status", "%" + query +"%"))	
	
	
	
	
	
	
	
	
).list();
	}


	@SuppressWarnings("unchecked")
	public List<Cliente> listClientesPagination(Cliente cliente, int page, int size) {
			//cuentaProxy.set#columnsGrid(cuenta.get#columnsGrid());
			return (List<Cliente>) sessionFactory.getCurrentSession()
				.createCriteria(Cliente.class).setFirstResult((page - 1) * size)
				.setMaxResults(size).list();
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRows() {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Cliente.class).setProjection(Projections.rowCount())
		.uniqueResult();	
		return totalRows;  
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRowsSearch(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Cliente.class).setProjection(Projections.rowCount())
					.add(	
							Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(Restrictions.or(	
						Restrictions.like("direccion", "%" + query +"%"),Restrictions.like("modalcliente", "%" + query +"%")),Restrictions.like("genero", "%" + query +"%")),Restrictions.like("nombre", "%" + query +"%")),Restrictions.like("rfc", "%" + query +"%")),Restrictions.like("gridcliente", "%" + query +"%")),Restrictions.like("numero", "%" + query +"%")),Restrictions.like("estado", "%" + query +"%")),Restrictions.like("status", "%" + query +"%"))	
	
	
	
	
	
	
	
	
).uniqueResult();
		return totalRows;  
	}


	@SuppressWarnings({ "unchecked" })
	public long getTotalRows(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Cliente.class).setProjection(Projections.rowCount())
		
		.uniqueResult();
		return totalRows;  
	}

	

	public Cliente getCliente(int empid) {
		return (Cliente) sessionFactory.getCurrentSession().get(
				Cliente.class, empid);
	}

	public void deleteCliente(Cliente cliente) {
		sessionFactory.getCurrentSession().delete(cliente);
	}

}
