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

import com.softtek.acceleo.demo.wizard.catalogo.bean.ClienteBean;
import com.softtek.acceleo.demo.wizard.domain.Cliente;
import com.softtek.acceleo.demo.wizard.service.ClienteService;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	Cliente cliente = new Cliente();

	@RequestMapping(value = "/cliente", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody  List<Cliente> getClientes(@RequestParam Map<String,String> requestParams, HttpServletRequest request, HttpServletResponse response) {

       	String query=requestParams.get("q");
		int _page= requestParams.get("_page")==null?0:new Integer(requestParams.get("_page")).intValue();
		long rows = 0;

		List<Cliente> listCliente = null;

		if (query==null && _page == 0) {
       		listCliente = clienteService.listClientes(cliente);
			rows = clienteService.getTotalRows();
		} else if (query!= null){
			listCliente = clienteService.listClientesQuery(cliente, query, _page, 10);
			rows = clienteService.getTotalRowsSearch(query);
		} else if (_page != 0){
			listCliente = clienteService.listClientesPagination(cliente, _page, 10);
			rows = clienteService.getTotalRows();
		}

		response.setHeader("Access-Control-Expose-Headers", "x-total-count");
		response.setHeader("x-total-count", String.valueOf(rows).toString());	

		return listCliente;
	}
	
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody  Cliente getCliente(@PathVariable("id") int id) {
	        
	        cliente.setId(id);
	        
	        Cliente cliente = null;
	        cliente = clienteService.getCliente(id);
			return cliente;
	 }



	 @RequestMapping(value = "/cliente", method = RequestMethod.POST)
	    public ResponseEntity<Void> createCliente(@RequestBody Cliente cliente,    UriComponentsBuilder ucBuilder) {
	   
	        clienteService.addCliente(cliente);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
		
	 @RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Cliente> actualizarCliente(@PathVariable("id") int id, @RequestBody Cliente cliente) {
	        
	        
	        Cliente clienteFound = clienteService.getCliente(id);
	         
	        if (clienteFound==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
	        }
	 
				clienteFound.setRfc(cliente.getRfc());
				clienteFound.setNumero(cliente.getNumero());
				clienteFound.setDireccion(cliente.getDireccion());
				clienteFound.setNombre(cliente.getNombre());
				clienteFound.setGenero(cliente.getGenero());
				clienteFound.setStatus(cliente.getStatus());
				clienteFound.setEstado(cliente.getEstado());
				clienteFound.setModalCliente(cliente.getModalCliente());
				clienteFound.setGridCliente(cliente.getGridCliente());
			cliente.setId(null);
	        
	        clienteService.editCliente(clienteFound);
	        return new ResponseEntity<Cliente>(clienteFound, HttpStatus.OK);
	  } 	
	
		
		@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Cliente> deleteCliente(@PathVariable("id") int id) {
	    	 System.out.println("Fetching & Deleting User with id " + id);
			 long rows = 0;	
	    	 
	         Cliente cliente = clienteService.getCliente(id);
	         if (cliente == null) {
	             System.out.println("Unable to delete. Cuenta with id " + id + " not found");
	             return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
	         }
	  
             if (rows==0){
	             clienteService.deleteCliente(cliente);
            	 return new ResponseEntity<Cliente>(HttpStatus.OK);
             } else {
            	 return new ResponseEntity<Cliente>(HttpStatus.PRECONDITION_FAILED);
             }
			
		}


	@RequestMapping(value = "/saveCliente", method = RequestMethod.POST)
	public @ResponseBody String saveCliente(
			@ModelAttribute("command") ClienteBean clienteBean) {


		Cliente cliente = prepareModel(clienteBean);
		clienteService.addCliente(cliente);

		return "SUCCESS";
	}
	
	@RequestMapping(value = "/editCliente", method = RequestMethod.POST)
	public @ResponseBody String editCliente(
			@ModelAttribute("command") ClienteBean clienteBean) {


		Cliente cliente = prepareModel(clienteBean);
		clienteService.editCliente(cliente);
		return "SUCCESS";
	}

	@RequestMapping(value = "/searchCliente", method = RequestMethod.GET)
	public ModelAndView addCliente(
			@ModelAttribute("command") ClienteBean clienteBean,
			BindingResult result) {

		Map<String, Object> model = new HashMap<String, Object>();
		Cliente cliente = null;
		if (clienteBean != null)
			cliente = prepareModel(clienteBean);
		model.put("clientes",
				prepareListofBean(clienteService.listClientes(cliente)));
		return new ModelAndView("searchCliente", model);
	}

	@RequestMapping(value = "/deleteCliente", method = RequestMethod.POST)
	public ModelAndView deleteCliente(
			@ModelAttribute("command") ClienteBean clienteBean,
			BindingResult result) {
		System.out.println("delete " + clienteBean.getId());
		clienteService.deleteCliente(prepareModel(clienteBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("cliente", null);
		model.put("clientes",
				prepareListofBean(clienteService.listClientes(null)));
		return new ModelAndView("searchCliente", model);
	}

	@RequestMapping(value = "/entryCliente", method = RequestMethod.GET)
	public ModelAndView entryCliente() {
		return new ModelAndView("redirect:/searchCliente");
	}

	private Cliente prepareModel(ClienteBean clienteBean) {
		Cliente cliente = new Cliente();

		cliente.setRfc(clienteBean.getRfc());
		cliente.setNumero(clienteBean.getNumero());
		cliente.setDireccion(clienteBean.getDireccion());
		cliente.setNombre(clienteBean.getNombre());
		cliente.setGenero(clienteBean.getGenero());
		cliente.setStatus(clienteBean.getStatus());
		cliente.setEstado(clienteBean.getEstado());
		cliente.setModalCliente(clienteBean.getModalCliente());
		cliente.setGridCliente(clienteBean.getGridCliente());
		cliente.setId(clienteBean.getId());
		clienteBean.setId(null);
		return cliente;
	}

	private List<ClienteBean> prepareListofBean(List<Cliente> clientes) {
		List<ClienteBean> beans = null;
		if (clientes != null && !clientes.isEmpty()) {
			beans = new ArrayList<ClienteBean>();
			ClienteBean bean = null;
			for (Cliente cliente : clientes) {
				bean = new ClienteBean();

                bean.setRfc(cliente.getRfc());
                bean.setNumero(cliente.getNumero());
                bean.setDireccion(cliente.getDireccion());
                bean.setNombre(cliente.getNombre());
                bean.setGenero(cliente.getGenero());
                bean.setStatus(cliente.getStatus());
                bean.setEstado(cliente.getEstado());
                bean.setModalCliente(cliente.getModalCliente());
                bean.setGridCliente(cliente.getGridCliente());
				bean.setId(cliente.getId());
				beans.add(bean);
			}
		}
		return beans;
	}

}


