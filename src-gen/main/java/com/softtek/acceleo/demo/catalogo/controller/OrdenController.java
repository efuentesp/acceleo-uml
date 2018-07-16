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


import com.softtek.acceleo.demo.catalogo.bean.OrdenBean;
import com.softtek.acceleo.demo.domain.Orden;
import com.softtek.acceleo.demo.service.OrdenService;

@Controller
public class OrdenController {

	@Autowired
	private OrdenService ordenService;
	
	Orden orden = new Orden();

	@RequestMapping(value = "/orden", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody  List<Orden> getOrdens(@RequestParam Map<String,String> requestParams, HttpServletRequest request, HttpServletResponse response) {

       	String query=requestParams.get("q");
		int _page= requestParams.get("_page")==null?0:new Integer(requestParams.get("_page")).intValue();
		long rows = 0;

		String strCliente=requestParams.get("cliente_id");			
		

		List<Orden> listOrden = null;

		if (query==null && _page == 0 && strCliente == null) {
       		listOrden = ordenService.listOrdenss(orden);
			rows = ordenService.getTotalRows();
		} else if (query!= null){
			if (strCliente != null) {
						listOrden = ordenService.listOrdenssQueryByCliente(orden, query, strCliente, _page, 10);
						rows = ordenService.getTotalRows(strCliente, query);
						} else {
						listOrden = ordenService.listOrdenssQuery(orden, query, _page, 10);
						rows = ordenService.getTotalRowsSearch(query);
			}
			
		} else if (_page != 0){
			listOrden = ordenService.listOrdensPagination(orden, _page, 10, strCliente
			);
			rows = ordenService.getTotalRows(strCliente
			);
		} else if (strCliente != null) {
					listOrden = ordenService.listOrdensFilterMaster(orden, strCliente, _page, 10);
					rows = ordenService.getTotalRows(strCliente);
				}	

		response.setHeader("Access-Control-Expose-Headers", "x-total-count");
		response.setHeader("x-total-count", String.valueOf(rows).toString());	

		return listOrden;
	}
	
	@RequestMapping(value = "/orden/{id}", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody  Orden getOrden(@PathVariable("id") int id) {
	        
	        orden.setId(id);
	        
	        Orden orden = null;
	        orden = ordenService.getOrden(id);
			return orden;
	 }



	 @RequestMapping(value = "/orden", method = RequestMethod.POST)
	    public ResponseEntity<Void> createOrden(@RequestBody Orden orden,    UriComponentsBuilder ucBuilder) {
	   
	        ordenService.addOrden(orden);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/orden/{id}").buildAndExpand(orden.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
		
	 @RequestMapping(value = "/orden/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Orden> actualizarOrden(@PathVariable("id") int id, @RequestBody Orden orden) {
	        
	        
	        Orden ordenFound = ordenService.getOrden(id);
	         
	        if (ordenFound==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Orden>(HttpStatus.NOT_FOUND);
	        }
	 
				ordenFound.setNumero(orden.getNumero());
				ordenFound.setAlmacen(orden.getAlmacen());
				ordenFound.setFecha(orden.getFecha());
				ordenFound.setCliente_id(orden.getCliente_id());
				ordenFound.setEstatus(orden.getEstatus());
			orden.setId(null);
	        
	        ordenService.editOrden(ordenFound);
	        return new ResponseEntity<Orden>(ordenFound, HttpStatus.OK);
	  } 	
	
		
		@RequestMapping(value = "/orden/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Orden> deleteOrden(@PathVariable("id") int id) {
	    	 System.out.println("Fetching & Deleting User with id " + id);
			 long rows = 0;	
	    	 
	         Orden orden = ordenService.getOrden(id);
	         if (orden == null) {
	             System.out.println("Unable to delete. Cuenta with id " + id + " not found");
	             return new ResponseEntity<Orden>(HttpStatus.NOT_FOUND);
	         }
	  
             

             if (rows==0){
	             ordenService.deleteOrden(orden);
            	 return new ResponseEntity<Orden>(HttpStatus.OK);
             } else {
            	 return new ResponseEntity<Orden>(HttpStatus.PRECONDITION_FAILED);
             }
			
		}


	@RequestMapping(value = "/saveOrden", method = RequestMethod.POST)
	public @ResponseBody String saveOrden(
			@ModelAttribute("command") OrdenBean ordenBean) {


		Orden orden = prepareModel(ordenBean);
		ordenService.addOrden(orden);

		return "SUCCESS";
	}
	
	@RequestMapping(value = "/editOrden", method = RequestMethod.POST)
	public @ResponseBody String editOrden(
			@ModelAttribute("command") OrdenBean ordenBean) {


		Orden orden = prepareModel(ordenBean);
		ordenService.editOrden(orden);
		return "SUCCESS";
	}

	@RequestMapping(value = "/searchOrden", method = RequestMethod.GET)
	public ModelAndView addOrden(
			@ModelAttribute("command") OrdenBean ordenBean,
			BindingResult result) {

		Map<String, Object> model = new HashMap<String, Object>();
		Orden orden = null;
		if (ordenBean != null)
			orden = prepareModel(ordenBean);
		model.put("ordens",
				prepareListofBean(ordenService.listOrdenss(orden)));
		return new ModelAndView("searchOrden", model);
	}

	@RequestMapping(value = "/deleteOrden", method = RequestMethod.POST)
	public ModelAndView deleteOrden(
			@ModelAttribute("command") OrdenBean ordenBean,
			BindingResult result) {
		System.out.println("delete " + ordenBean.getId());
		ordenService.deleteOrden(prepareModel(ordenBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("orden", null);
		model.put("ordens",
				prepareListofBean(ordenService.listOrdenss(null)));
		return new ModelAndView("searchOrden", model);
	}

	@RequestMapping(value = "/entryOrden", method = RequestMethod.GET)
	public ModelAndView entryOrden() {
		return new ModelAndView("redirect:/searchOrden");
	}

	private Orden prepareModel(OrdenBean ordenBean) {
		Orden orden = new Orden();

		orden.setNumero(ordenBean.getNumero());
		orden.setAlmacen(ordenBean.getAlmacen());
		orden.setFecha(ordenBean.getFecha());
		orden.setCliente_id(ordenBean.getCliente_id());
		orden.setEstatus(ordenBean.getEstatus());
		orden.setId(ordenBean.getId());
		ordenBean.setId(null);
		return orden;
	}

	private List<OrdenBean> prepareListofBean(List<Orden> ordens) {
		List<OrdenBean> beans = null;
		if (ordens != null && !ordens.isEmpty()) {
			beans = new ArrayList<OrdenBean>();
			OrdenBean bean = null;
			for (Orden orden : ordens) {
				bean = new OrdenBean();

                bean.setNumero(orden.getNumero());
                bean.setAlmacen(orden.getAlmacen());
                bean.setFecha(orden.getFecha());
                bean.setCliente_id(orden.getCliente_id());
                bean.setEstatus(orden.getEstatus());
				bean.setId(orden.getId());
				beans.add(bean);
			}
		}
		return beans;
	}

}


