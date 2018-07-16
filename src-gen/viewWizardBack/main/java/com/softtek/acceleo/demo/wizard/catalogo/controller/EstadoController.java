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

import com.softtek.acceleo.demo.wizard.catalogo.bean.EstadoBean;
import com.softtek.acceleo.demo.wizard.domain.Estado;
import com.softtek.acceleo.demo.wizard.service.EstadoService;

@Controller
public class EstadoController {

	@Autowired
	private EstadoService estadoService;
	
	Estado estado = new Estado();

	@RequestMapping(value = "/estado", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody  List<Estado> getEstados(@RequestParam Map<String,String> requestParams, HttpServletRequest request, HttpServletResponse response) {

       	String query=requestParams.get("q");
		int _page= requestParams.get("_page")==null?0:new Integer(requestParams.get("_page")).intValue();
		long rows = 0;

		List<Estado> listEstado = null;

		if (query==null && _page == 0) {
       		listEstado = estadoService.listEstados(estado);
			rows = estadoService.getTotalRows();
		} else if (query!= null){
			listEstado = estadoService.listEstadosQuery(estado, query, _page, 10);
			rows = estadoService.getTotalRowsSearch(query);
		} else if (_page != 0){
			listEstado = estadoService.listEstadosPagination(estado, _page, 10);
			rows = estadoService.getTotalRows();
		}

		response.setHeader("Access-Control-Expose-Headers", "x-total-count");
		response.setHeader("x-total-count", String.valueOf(rows).toString());	

		return listEstado;
	}
	
	@RequestMapping(value = "/estado/{id}", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody  Estado getEstado(@PathVariable("id") int id) {
	        
	        estado.setId(id);
	        
	        Estado estado = null;
	        estado = estadoService.getEstado(id);
			return estado;
	 }



	 @RequestMapping(value = "/estado", method = RequestMethod.POST)
	    public ResponseEntity<Void> createEstado(@RequestBody Estado estado,    UriComponentsBuilder ucBuilder) {
	   
	        estadoService.addEstado(estado);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/estado/{id}").buildAndExpand(estado.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
		
	 @RequestMapping(value = "/estado/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Estado> actualizarEstado(@PathVariable("id") int id, @RequestBody Estado estado) {
	        
	        
	        Estado estadoFound = estadoService.getEstado(id);
	         
	        if (estadoFound==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Estado>(HttpStatus.NOT_FOUND);
	        }
	 
				estadoFound.setNombre(estado.getNombre());
				estadoFound.setValor(estado.getValor());
			estado.setId(null);
	        
	        estadoService.editEstado(estadoFound);
	        return new ResponseEntity<Estado>(estadoFound, HttpStatus.OK);
	  } 	
	
		
		@RequestMapping(value = "/estado/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Estado> deleteEstado(@PathVariable("id") int id) {
	    	 System.out.println("Fetching & Deleting User with id " + id);
			 long rows = 0;	
	    	 
	         Estado estado = estadoService.getEstado(id);
	         if (estado == null) {
	             System.out.println("Unable to delete. Cuenta with id " + id + " not found");
	             return new ResponseEntity<Estado>(HttpStatus.NOT_FOUND);
	         }
	  
             if (rows==0){
	             estadoService.deleteEstado(estado);
            	 return new ResponseEntity<Estado>(HttpStatus.OK);
             } else {
            	 return new ResponseEntity<Estado>(HttpStatus.PRECONDITION_FAILED);
             }
			
		}


	@RequestMapping(value = "/saveEstado", method = RequestMethod.POST)
	public @ResponseBody String saveEstado(
			@ModelAttribute("command") EstadoBean estadoBean) {


		Estado estado = prepareModel(estadoBean);
		estadoService.addEstado(estado);

		return "SUCCESS";
	}
	
	@RequestMapping(value = "/editEstado", method = RequestMethod.POST)
	public @ResponseBody String editEstado(
			@ModelAttribute("command") EstadoBean estadoBean) {


		Estado estado = prepareModel(estadoBean);
		estadoService.editEstado(estado);
		return "SUCCESS";
	}

	@RequestMapping(value = "/searchEstado", method = RequestMethod.GET)
	public ModelAndView addEstado(
			@ModelAttribute("command") EstadoBean estadoBean,
			BindingResult result) {

		Map<String, Object> model = new HashMap<String, Object>();
		Estado estado = null;
		if (estadoBean != null)
			estado = prepareModel(estadoBean);
		model.put("estados",
				prepareListofBean(estadoService.listEstados(estado)));
		return new ModelAndView("searchEstado", model);
	}

	@RequestMapping(value = "/deleteEstado", method = RequestMethod.POST)
	public ModelAndView deleteEstado(
			@ModelAttribute("command") EstadoBean estadoBean,
			BindingResult result) {
		System.out.println("delete " + estadoBean.getId());
		estadoService.deleteEstado(prepareModel(estadoBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("estado", null);
		model.put("estados",
				prepareListofBean(estadoService.listEstados(null)));
		return new ModelAndView("searchEstado", model);
	}

	@RequestMapping(value = "/entryEstado", method = RequestMethod.GET)
	public ModelAndView entryEstado() {
		return new ModelAndView("redirect:/searchEstado");
	}

	private Estado prepareModel(EstadoBean estadoBean) {
		Estado estado = new Estado();

		estado.setNombre(estadoBean.getNombre());
		estado.setValor(estadoBean.getValor());
		estado.setId(estadoBean.getId());
		estadoBean.setId(null);
		return estado;
	}

	private List<EstadoBean> prepareListofBean(List<Estado> estados) {
		List<EstadoBean> beans = null;
		if (estados != null && !estados.isEmpty()) {
			beans = new ArrayList<EstadoBean>();
			EstadoBean bean = null;
			for (Estado estado : estados) {
				bean = new EstadoBean();

                bean.setNombre(estado.getNombre());
                bean.setValor(estado.getValor());
				bean.setId(estado.getId());
				beans.add(bean);
			}
		}
		return beans;
	}

}


