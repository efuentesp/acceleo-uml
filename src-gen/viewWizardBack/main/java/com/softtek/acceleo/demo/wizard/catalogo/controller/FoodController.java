package com.softtek.acceleo.demo.wizard.catalogo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.softtek.acceleo.demo.wizard.catalogo.bean.FoodBean;
import com.softtek.acceleo.demo.wizard.domain.Food;
import com.softtek.acceleo.demo.wizard.service.FoodService;

@Controller
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	Food food = new Food();

	@RequestMapping(value = "/food", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody  List<Food> getFoods(@RequestParam Map<String,String> requestParams, HttpServletRequest request, HttpServletResponse response) {

       	String query=requestParams.get("q");
		int _page= requestParams.get("_page")==null?0:new Integer(requestParams.get("_page")).intValue();
		long rows = 0;

		List<Food> listFood = null;

		if (query==null && _page == 0) {
       		listFood = foodService.listFoods(food);
			rows = foodService.getTotalRows();
		} else if (query!= null){
			listFood = foodService.listFoodsQuery(food, _page, 10);
			rows = foodService.getTotalRowsSearch(query);
		} else if (_page != 0){
			listFood = foodService.listFoodsPagination(food, _page, 10);
			rows = foodService.getTotalRows();
		}

		response.setHeader("Access-Control-Expose-Headers", "x-total-count");
		response.setHeader("x-total-count", String.valueOf(rows).toString());	

		return listFood;
	}
	
	@RequestMapping(value = "/food/{id}", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody  Food getFood(@PathVariable("id") int id) {
	        
	        food.setId(id);
	        
	        Food food = null;
	        food = foodService.getFood(id);
			return food;
	 }



	 @RequestMapping(value = "/food", method = RequestMethod.POST)
	    public ResponseEntity<Void> createFood(@RequestBody Food food,    UriComponentsBuilder ucBuilder) {
	   
	        foodService.addFood(food);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/food/{id}").buildAndExpand(food.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
		
	 @RequestMapping(value = "/food/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Food> actualizarFood(@PathVariable("id") int id, @RequestBody Food food) {
	        
	        
	        Food foodFound = foodService.getFood(id);
	         
	        if (foodFound==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Food>(HttpStatus.NOT_FOUND);
	        }
	 
				foodFound.setNombre(food.getNombre());
				foodFound.setValor(food.getValor());
			food.setId(null);
	        
	        foodService.editFood(foodFound);
	        return new ResponseEntity<Food>(foodFound, HttpStatus.OK);
	  } 	
	
		
		@RequestMapping(value = "/food/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Food> deleteFood(@PathVariable("id") int id) {
	    	 System.out.println("Fetching & Deleting User with id " + id);
			 long rows = 0;	
	    	 
	         Food food = foodService.getFood(id);
	         if (food == null) {
	             System.out.println("Unable to delete. Cuenta with id " + id + " not found");
	             return new ResponseEntity<Food>(HttpStatus.NOT_FOUND);
	         }
	  
             if (rows==0){
	             foodService.deleteFood(food);
            	 return new ResponseEntity<Food>(HttpStatus.OK);
             } else {
            	 return new ResponseEntity<Food>(HttpStatus.PRECONDITION_FAILED);
             }
			
		}


	@RequestMapping(value = "/saveFood", method = RequestMethod.POST)
	public @ResponseBody String saveFood(
			@ModelAttribute("command") FoodBean foodBean) {


		Food food = prepareModel(foodBean);
		foodService.addFood(food);

		return "SUCCESS";
	}
	
	@RequestMapping(value = "/editFood", method = RequestMethod.POST)
	public @ResponseBody String editFood(
			@ModelAttribute("command") FoodBean foodBean) {


		Food food = prepareModel(foodBean);
		foodService.editFood(food);
		return "SUCCESS";
	}

	@RequestMapping(value = "/searchFood", method = RequestMethod.GET)
	public ModelAndView addFood(
			@ModelAttribute("command") FoodBean foodBean,
			BindingResult result) {

		Map<String, Object> model = new HashMap<String, Object>();
		Food food = null;
		if (foodBean != null)
			food = prepareModel(foodBean);
		model.put("foods",
				prepareListofBean(foodService.listFoodss(food)));
		return new ModelAndView("searchFood", model);
	}

	@RequestMapping(value = "/deleteFood", method = RequestMethod.POST)
	public ModelAndView deleteFood(
			@ModelAttribute("command") FoodBean foodBean,
			BindingResult result) {
		System.out.println("delete " + foodBean.getId());
		foodService.deleteFood(prepareModel(foodBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("food", null);
		model.put("foods",
				prepareListofBean(foodService.listFoodss(null)));
		return new ModelAndView("searchFood", model);
	}

	@RequestMapping(value = "/entryFood", method = RequestMethod.GET)
	public ModelAndView entryFood() {
		return new ModelAndView("redirect:/searchFood");
	}

	private Food prepareModel(FoodBean foodBean) {
		Food food = new Food();

		food.setNombre(foodBean.getNombre());
		food.setValor(foodBean.getValor());
		food.setId(foodBean.getId());
		foodBean.setId(null);
		return food;
	}

	private List<FoodBean> prepareListofBean(List<Food> foods) {
		List<FoodBean> beans = null;
		if (foods != null && !foods.isEmpty()) {
			beans = new ArrayList<FoodBean>();
			FoodBean bean = null;
			for (Food food : foods) {
				bean = new FoodBean();

                bean.setNombre(food.getNombre());
                bean.setValor(food.getValor());
				bean.setId(food.getId());
				beans.add(bean);
			}
		}
		return beans;
	}

}


