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

import com.softtek.acceleo.demo.wizard.catalogo.bean.GeneroBean;
import com.softtek.acceleo.demo.wizard.domain.Genero;
import com.softtek.acceleo.demo.wizard.service.GeneroService;

@Controller
public class GeneroController {

	@Autowired
	private GeneroService generoService;
	
	Genero genero = new Genero();

	@RequestMapping(value = "/genero", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody  List<Genero> getGeneros(@RequestParam Map<String,String> requestParams, HttpServletRequest request, HttpServletResponse response) {

       	String query=requestParams.get("q");
		int _page= requestParams.get("_page")==null?0:new Integer(requestParams.get("_page")).intValue();
		long rows = 0;

		List<Genero> listGenero = null;

		if (query==null && _page == 0) {
       		listGenero = generoService.listGeneros(genero);
			rows = generoService.getTotalRows();
		} else if (query!= null){
			listGenero = generoService.listGenerosQuery(genero, query, _page, 10);
			rows = generoService.getTotalRowsSearch(query);
		} else if (_page != 0){
			listGenero = generoService.listGenerosPagination(genero, _page, 10);
			rows = generoService.getTotalRows();
		}

		response.setHeader("Access-Control-Expose-Headers", "x-total-count");
		response.setHeader("x-total-count", String.valueOf(rows).toString());	

		return listGenero;
	}
	
	@RequestMapping(value = "/genero/{id}", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody  Genero getGenero(@PathVariable("id") int id) {
	        
	        genero.setId(id);
	        
	        Genero genero = null;
	        genero = generoService.getGenero(id);
			return genero;
	 }



	 @RequestMapping(value = "/genero", method = RequestMethod.POST)
	    public ResponseEntity<Void> createGenero(@RequestBody Genero genero,    UriComponentsBuilder ucBuilder) {
	   
	        generoService.addGenero(genero);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/genero/{id}").buildAndExpand(genero.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
		
	 @RequestMapping(value = "/genero/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Genero> actualizarGenero(@PathVariable("id") int id, @RequestBody Genero genero) {
	        
	        
	        Genero generoFound = generoService.getGenero(id);
	         
	        if (generoFound==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Genero>(HttpStatus.NOT_FOUND);
	        }
	 
				generoFound.setNombre(genero.getNombre());
				generoFound.setValor(genero.getValor());
			genero.setId(null);
	        
	        generoService.editGenero(generoFound);
	        return new ResponseEntity<Genero>(generoFound, HttpStatus.OK);
	  } 	
	
		
		@RequestMapping(value = "/genero/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Genero> deleteGenero(@PathVariable("id") int id) {
	    	 System.out.println("Fetching & Deleting User with id " + id);
			 long rows = 0;	
	    	 
	         Genero genero = generoService.getGenero(id);
	         if (genero == null) {
	             System.out.println("Unable to delete. Cuenta with id " + id + " not found");
	             return new ResponseEntity<Genero>(HttpStatus.NOT_FOUND);
	         }
	  
             if (rows==0){
	             generoService.deleteGenero(genero);
            	 return new ResponseEntity<Genero>(HttpStatus.OK);
             } else {
            	 return new ResponseEntity<Genero>(HttpStatus.PRECONDITION_FAILED);
             }
			
		}


	@RequestMapping(value = "/saveGenero", method = RequestMethod.POST)
	public @ResponseBody String saveGenero(
			@ModelAttribute("command") GeneroBean generoBean) {


		Genero genero = prepareModel(generoBean);
		generoService.addGenero(genero);

		return "SUCCESS";
	}
	
	@RequestMapping(value = "/editGenero", method = RequestMethod.POST)
	public @ResponseBody String editGenero(
			@ModelAttribute("command") GeneroBean generoBean) {


		Genero genero = prepareModel(generoBean);
		generoService.editGenero(genero);
		return "SUCCESS";
	}

	@RequestMapping(value = "/searchGenero", method = RequestMethod.GET)
	public ModelAndView addGenero(
			@ModelAttribute("command") GeneroBean generoBean,
			BindingResult result) {

		Map<String, Object> model = new HashMap<String, Object>();
		Genero genero = null;
		if (generoBean != null)
			genero = prepareModel(generoBean);
		model.put("generos",
				prepareListofBean(generoService.listGeneros(genero)));
		return new ModelAndView("searchGenero", model);
	}

	@RequestMapping(value = "/deleteGenero", method = RequestMethod.POST)
	public ModelAndView deleteGenero(
			@ModelAttribute("command") GeneroBean generoBean,
			BindingResult result) {
		System.out.println("delete " + generoBean.getId());
		generoService.deleteGenero(prepareModel(generoBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("genero", null);
		model.put("generos",
				prepareListofBean(generoService.listGeneros(null)));
		return new ModelAndView("searchGenero", model);
	}

	@RequestMapping(value = "/entryGenero", method = RequestMethod.GET)
	public ModelAndView entryGenero() {
		return new ModelAndView("redirect:/searchGenero");
	}

	private Genero prepareModel(GeneroBean generoBean) {
		Genero genero = new Genero();

		genero.setNombre(generoBean.getNombre());
		genero.setValor(generoBean.getValor());
		genero.setId(generoBean.getId());
		generoBean.setId(null);
		return genero;
	}

	private List<GeneroBean> prepareListofBean(List<Genero> generos) {
		List<GeneroBean> beans = null;
		if (generos != null && !generos.isEmpty()) {
			beans = new ArrayList<GeneroBean>();
			GeneroBean bean = null;
			for (Genero genero : generos) {
				bean = new GeneroBean();

                bean.setNombre(genero.getNombre());
                bean.setValor(genero.getValor());
				bean.setId(genero.getId());
				beans.add(bean);
			}
		}
		return beans;
	}

}


