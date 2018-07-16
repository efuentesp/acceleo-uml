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

import com.softtek.acceleo.demo.wizard.catalogo.bean.StatusBean;
import com.softtek.acceleo.demo.wizard.domain.Status;
import com.softtek.acceleo.demo.wizard.service.StatusService;

@Controller
public class StatusController {

	@Autowired
	private StatusService statusService;
	
	Status status = new Status();

	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody  List<Status> getStatuss(@RequestParam Map<String,String> requestParams, HttpServletRequest request, HttpServletResponse response) {

       	String query=requestParams.get("q");
		int _page= requestParams.get("_page")==null?0:new Integer(requestParams.get("_page")).intValue();
		long rows = 0;

		List<Status> listStatus = null;

		if (query==null && _page == 0) {
       		listStatus = statusService.listStatuss(status);
			rows = statusService.getTotalRows();
		} else if (query!= null){
			listStatus = statusService.listStatussQuery(status, query, _page, 10);
			rows = statusService.getTotalRowsSearch(query);
		} else if (_page != 0){
			listStatus = statusService.listStatussPagination(status, _page, 10);
			rows = statusService.getTotalRows();
		}

		response.setHeader("Access-Control-Expose-Headers", "x-total-count");
		response.setHeader("x-total-count", String.valueOf(rows).toString());	

		return listStatus;
	}
	
	@RequestMapping(value = "/status/{id}", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody  Status getStatus(@PathVariable("id") int id) {
	        
	        status.setId(id);
	        
	        Status status = null;
	        status = statusService.getStatus(id);
			return status;
	 }



	 @RequestMapping(value = "/status", method = RequestMethod.POST)
	    public ResponseEntity<Void> createStatus(@RequestBody Status status,    UriComponentsBuilder ucBuilder) {
	   
	        statusService.addStatus(status);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/status/{id}").buildAndExpand(status.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
		
	 @RequestMapping(value = "/status/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Status> actualizarStatus(@PathVariable("id") int id, @RequestBody Status status) {
	        
	        
	        Status statusFound = statusService.getStatus(id);
	         
	        if (statusFound==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Status>(HttpStatus.NOT_FOUND);
	        }
	 
				statusFound.setNombre(status.getNombre());
				statusFound.setValor(status.getValor());
			status.setId(null);
	        
	        statusService.editStatus(statusFound);
	        return new ResponseEntity<Status>(statusFound, HttpStatus.OK);
	  } 	
	
		
		@RequestMapping(value = "/status/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Status> deleteStatus(@PathVariable("id") int id) {
	    	 System.out.println("Fetching & Deleting User with id " + id);
			 long rows = 0;	
	    	 
	         Status status = statusService.getStatus(id);
	         if (status == null) {
	             System.out.println("Unable to delete. Cuenta with id " + id + " not found");
	             return new ResponseEntity<Status>(HttpStatus.NOT_FOUND);
	         }
	  
             if (rows==0){
	             statusService.deleteStatus(status);
            	 return new ResponseEntity<Status>(HttpStatus.OK);
             } else {
            	 return new ResponseEntity<Status>(HttpStatus.PRECONDITION_FAILED);
             }
			
		}


	@RequestMapping(value = "/saveStatus", method = RequestMethod.POST)
	public @ResponseBody String saveStatus(
			@ModelAttribute("command") StatusBean statusBean) {


		Status status = prepareModel(statusBean);
		statusService.addStatus(status);

		return "SUCCESS";
	}
	
	@RequestMapping(value = "/editStatus", method = RequestMethod.POST)
	public @ResponseBody String editStatus(
			@ModelAttribute("command") StatusBean statusBean) {


		Status status = prepareModel(statusBean);
		statusService.editStatus(status);
		return "SUCCESS";
	}

	@RequestMapping(value = "/searchStatus", method = RequestMethod.GET)
	public ModelAndView addStatus(
			@ModelAttribute("command") StatusBean statusBean,
			BindingResult result) {

		Map<String, Object> model = new HashMap<String, Object>();
		Status status = null;
		if (statusBean != null)
			status = prepareModel(statusBean);
		model.put("statuss",
				prepareListofBean(statusService.listStatuss(status)));
		return new ModelAndView("searchStatus", model);
	}

	@RequestMapping(value = "/deleteStatus", method = RequestMethod.POST)
	public ModelAndView deleteStatus(
			@ModelAttribute("command") StatusBean statusBean,
			BindingResult result) {
		System.out.println("delete " + statusBean.getId());
		statusService.deleteStatus(prepareModel(statusBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", null);
		model.put("statuss",
				prepareListofBean(statusService.listStatuss(null)));
		return new ModelAndView("searchStatus", model);
	}

	@RequestMapping(value = "/entryStatus", method = RequestMethod.GET)
	public ModelAndView entryStatus() {
		return new ModelAndView("redirect:/searchStatus");
	}

	private Status prepareModel(StatusBean statusBean) {
		Status status = new Status();

		status.setNombre(statusBean.getNombre());
		status.setValor(statusBean.getValor());
		status.setId(statusBean.getId());
		statusBean.setId(null);
		return status;
	}

	private List<StatusBean> prepareListofBean(List<Status> statuss) {
		List<StatusBean> beans = null;
		if (statuss != null && !statuss.isEmpty()) {
			beans = new ArrayList<StatusBean>();
			StatusBean bean = null;
			for (Status status : statuss) {
				bean = new StatusBean();

                bean.setNombre(status.getNombre());
                bean.setValor(status.getValor());
				bean.setId(status.getId());
				beans.add(bean);
			}
		}
		return beans;
	}

}


