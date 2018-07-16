

package com.softtek.acceleo.demo.wizard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.acceleo.demo.wizard.repository.FoodRepository;
import com.softtek.acceleo.demo.wizard.domain.Food;
import com.softtek.acceleo.demo.wizard.service.FoodService;

@Service("foodService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addFood(Food food) {
		
		foodRepository.addFood(food);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editFood(Food food) {
		
		foodRepository.editFood(food);
	}
	
	
	public List<Food> listFoodss(Food food) {

		return foodRepository.listFoodss(food);
	}

	public Food getFood(int empid) {

		return foodRepository.getFood(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteFood(Food food) {
		System.out.println("Entrando al deleteFood");

		 foodRepository.deleteFood(food);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Food> listFoodsPagination(Food food, int page, int size) {

		return foodRepository.listFoodsPagination(food, page, size);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRowsSearch(String query) {

		return foodRepository.getTotalRowsSearch(query);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows(String query) {

		return foodRepository.getTotalRows(query);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows() {

		return foodRepository.getTotalRows();
	}



	public List<Food> listFoodssQuery(Food food, String query, int page, int size) {
		// TODO Auto-generated method stub
		return foodRepository.listFoodssQuery(food, query, page, size);
	}

}

