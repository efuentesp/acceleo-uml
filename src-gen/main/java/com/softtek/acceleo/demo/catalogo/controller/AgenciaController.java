package com.softtek.acceleo.demo.catalogo.controller;

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


import com.softtek.acceleo.demo.catalogo.bean.AgenciaBean;
import com.softtek.acceleo.demo.domain.Agencia;
import com.softtek.acceleo.demo.service.AgenciaService;

@Controller
public class AgenciaController {

	@Autowired
	private AgenciaService agenciaService;
	
	Agencia agencia = new Agencia();

	@RequestMapping(value = "/agencia", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody  List<Agencia> getAgencias(@RequestParam Map<String,String> requestParams, HttpServletRequest request, HttpServletResponse response) {

       	String query=requestParams.get("q");
		int _page= requestParams.get("_page")==null?0:new Integer(requestParams.get("_page")).intValue();
		long rows = 0;

		

		List<Agencia> listAgencia = null;

		if (query==null && _page == 0 ) {
       		listAgencia = agenciaService.listAgenciass(agencia);
			rows = agenciaService.getTotalRows();
		} else if (query!= null){
				listAgencia = agenciaService.listAgenciassQuery(agencia, query, _page, 10);
				rows = agenciaService.getTotalRowsSearch(query);
			
		} else if (_page != 0){
			listAgencia = agenciaService.listAgenciasPagination(agencia, _page, 10);
			rows = agenciaService.getTotalRows();
		} 	

		response.setHeader("Access-Control-Expose-Headers", "x-total-count");
		response.setHeader("x-total-count", String.valueOf(rows).toString());	

		return listAgencia;
	}
	
	@RequestMapping(value = "/agencia/{id}", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody  Agencia getAgencia(@PathVariable("id") int id) {
	        
	        agencia.setId(id);
	        
	        Agencia agencia = null;
	        agencia = agenciaService.getAgencia(id);
			return agencia;
	 }



	 @RequestMapping(value = "/agencia", method = RequestMethod.POST)
	    public ResponseEntity<Void> createAgencia(@RequestBody Agencia agencia,    UriComponentsBuilder ucBuilder) {
	   
	        agenciaService.addAgencia(agencia);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/agencia/{id}").buildAndExpand(agencia.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
		
	 @RequestMapping(value = "/agencia/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Agencia> actualizarAgencia(@PathVariable("id") int id, @RequestBody Agencia agencia) {
	        
	        
	        Agencia agenciaFound = agenciaService.getAgencia(id);
	         
	        if (agenciaFound==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Agencia>(HttpStatus.NOT_FOUND);
	        }
	 
				agenciaFound.setNombre(agencia.getNombre());
				agenciaFound.setPais(agencia.getPais());
			agencia.setId(null);
	        
	        agenciaService.editAgencia(agenciaFound);
	        return new ResponseEntity<Agencia>(agenciaFound, HttpStatus.OK);
	  } 	
	
		
		@RequestMapping(value = "/agencia/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Agencia> deleteAgencia(@PathVariable("id") int id) {
	    	 System.out.println("Fetching & Deleting User with id " + id);
			 long rows = 0;	
	    	 
	         Agencia agencia = agenciaService.getAgencia(id);
	         if (agencia == null) {
	             System.out.println("Unable to delete. Cuenta with id " + id + " not found");
	             return new ResponseEntity<Agencia>(HttpStatus.NOT_FOUND);
	         }
	  
             

             if (rows==0){
	             agenciaService.deleteAgencia(agencia);
            	 return new ResponseEntity<Agencia>(HttpStatus.OK);
             } else {
            	 return new ResponseEntity<Agencia>(HttpStatus.PRECONDITION_FAILED);
             }
			
		}


	@RequestMapping(value = "/saveAgencia", method = RequestMethod.POST)
	public @ResponseBody String saveAgencia(
			@ModelAttribute("command") AgenciaBean agenciaBean) {


		Agencia agencia = prepareModel(agenciaBean);
		agenciaService.addAgencia(agencia);

		return "SUCCESS";
	}
	
	@RequestMapping(value = "/editAgencia", method = RequestMethod.POST)
	public @ResponseBody String editAgencia(
			@ModelAttribute("command") AgenciaBean agenciaBean) {


		Agencia agencia = prepareModel(agenciaBean);
		agenciaService.editAgencia(agencia);
		return "SUCCESS";
	}

	@RequestMapping(value = "/searchAgencia", method = RequestMethod.GET)
	public ModelAndView addAgencia(
			@ModelAttribute("command") AgenciaBean agenciaBean,
			BindingResult result) {

		Map<String, Object> model = new HashMap<String, Object>();
		Agencia agencia = null;
		if (agenciaBean != null)
			agencia = prepareModel(agenciaBean);
		model.put("agencias",
				prepareListofBean(agenciaService.listAgenciass(agencia)));
		return new ModelAndView("searchAgencia", model);
	}

	@RequestMapping(value = "/deleteAgencia", method = RequestMethod.POST)
	public ModelAndView deleteAgencia(
			@ModelAttribute("command") AgenciaBean agenciaBean,
			BindingResult result) {
		System.out.println("delete " + agenciaBean.getId());
		agenciaService.deleteAgencia(prepareModel(agenciaBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("agencia", null);
		model.put("agencias",
				prepareListofBean(agenciaService.listAgenciass(null)));
		return new ModelAndView("searchAgencia", model);
	}

	@RequestMapping(value = "/entryAgencia", method = RequestMethod.GET)
	public ModelAndView entryAgencia() {
		return new ModelAndView("redirect:/searchAgencia");
	}

	private Agencia prepareModel(AgenciaBean agenciaBean) {
		Agencia agencia = new Agencia();

		agencia.setNombre(agenciaBean.getNombre());
		agencia.setPais(agenciaBean.getPais());
		agencia.setId(agenciaBean.getId());
		agenciaBean.setId(null);
		return agencia;
	}

	private List<AgenciaBean> prepareListofBean(List<Agencia> agencias) {
		List<AgenciaBean> beans = null;
		if (agencias != null && !agencias.isEmpty()) {
			beans = new ArrayList<AgenciaBean>();
			AgenciaBean bean = null;
			for (Agencia agencia : agencias) {
				bean = new AgenciaBean();

                bean.setNombre(agencia.getNombre());
                bean.setPais(agencia.getPais());
				bean.setId(agencia.getId());
				beans.add(bean);
			}
		}
		return beans;
	}

}


