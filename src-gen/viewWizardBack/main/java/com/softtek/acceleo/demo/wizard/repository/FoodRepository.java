
package com.softtek.acceleo.demo.wizard.repository;

import java.util.List;
import com.softtek.acceleo.demo.wizard.domain.Food;

public interface FoodRepository {

	
	 public void addFood(Food food);   
	 
	 public void editFood(Food food);
	   
	 public List<Food> listFoodss(Food food);   
	    
	 public Food getFood(int empid);   
	    
	 public void deleteFood(Food food); 

	 public List<Food> listFoodssQuery(Food food, String query, int page, int size);

	 public List<Food> listFoodsPagination(Food food, int page, int size);	

     public long getTotalRows();

     public long getTotalRows(String query);

     public long getTotalRowsSearch(String query);

}

