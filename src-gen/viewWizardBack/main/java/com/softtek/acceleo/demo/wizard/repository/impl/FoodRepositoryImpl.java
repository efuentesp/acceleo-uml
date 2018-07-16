
package com.softtek.acceleo.demo.wizard.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.softtek.acceleo.demo.wizard.domain.Food;
import com.softtek.acceleo.demo.wizard.repository.FoodRepository;

@Repository("foodRepository")
public class FoodRepositoryImpl implements FoodRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void addFood(Food food) {
		sessionFactory.getCurrentSession().persist(food);
	}

	public void editFood(Food food) {
		sessionFactory.getCurrentSession().update(food);

	}

	@SuppressWarnings({ "unchecked" })
	public List<Food> listFoodss(Food food) {

		if (food != null) {

			Food foodProxy = new Food();




			return (List<Food>) sessionFactory.getCurrentSession()
					.createCriteria(Food.class)
					.add(Example.create(foodProxy)).list();

		}

		return (List<Food>) sessionFactory.getCurrentSession()
				.createCriteria(Food.class).list();

	}


	@SuppressWarnings("unchecked")
	public List<Food> listFoodssQuery(Food food, String query, int page, int size) {
			//foodProxy.set#columnsGrid(food.get#columnsGrid());
			return (List<Food>) sessionFactory.getCurrentSession()
					.createCriteria(Food.class).setFirstResult((page - 1) * size)
					.add(	
							Restrictions.or(	
						Restrictions.like("valor", "%" + query +"%"),Restrictions.like("nombre", "%" + query +"%"))	
	
).list();
	}


	@SuppressWarnings("unchecked")
	public List<Food> listFoodsPagination(Food food, int page, int size) {
			//cuentaProxy.set#columnsGrid(cuenta.get#columnsGrid());
			return (List<Food>) sessionFactory.getCurrentSession()
				.createCriteria(Food.class).setFirstResult((page - 1) * size)
				.setMaxResults(size).list();
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRows() {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Food.class).setProjection(Projections.rowCount())
		.uniqueResult();	
		return totalRows;  
	}

	@SuppressWarnings({ "unchecked" })
	public long getTotalRowsSearch(String query) {
		
		long totalRows = 0;
		totalRows = (Long) sessionFactory.getCurrentSession()
		.createCriteria(Food.class).setProjection(Projections.rowCount())
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
		.createCriteria(Food.class).setProjection(Projections.rowCount())
		
		.uniqueResult();
		return totalRows;  
	}

	

	public Food getFood(int empid) {
		return (Food) sessionFactory.getCurrentSession().get(
				Food.class, empid);
	}

	public void deleteFood(Food food) {
		sessionFactory.getCurrentSession().delete(food);
	}

}
