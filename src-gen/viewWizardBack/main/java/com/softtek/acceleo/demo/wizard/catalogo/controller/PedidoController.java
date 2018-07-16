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

import com.softtek.acceleo.demo.wizard.catalogo.bean.PedidoBean;
import com.softtek.acceleo.demo.wizard.domain.Pedido;
import com.softtek.acceleo.demo.wizard.service.PedidoService;

@Controller
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	Pedido pedido = new Pedido();

	@RequestMapping(value = "/pedido", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody  List<Pedido> getPedidos(@RequestParam Map<String,String> requestParams, HttpServletRequest request, HttpServletResponse response) {

       	String query=requestParams.get("q");
		int _page= requestParams.get("_page")==null?0:new Integer(requestParams.get("_page")).intValue();
		long rows = 0;

		List<Pedido> listPedido = null;

		if (query==null && _page == 0) {
       		listPedido = pedidoService.listPedidos(pedido);
			rows = pedidoService.getTotalRows();
		} else if (query!= null){
			listPedido = pedidoService.listPedidosQuery(pedido, query, _page, 10);
			rows = pedidoService.getTotalRowsSearch(query);
		} else if (_page != 0){
			listPedido = pedidoService.listPedidosPagination(pedido, _page, 10);
			rows = pedidoService.getTotalRows();
		}

		response.setHeader("Access-Control-Expose-Headers", "x-total-count");
		response.setHeader("x-total-count", String.valueOf(rows).toString());	

		return listPedido;
	}
	
	@RequestMapping(value = "/pedido/{id}", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody  Pedido getPedido(@PathVariable("id") int id) {
	        
	        pedido.setId(id);
	        
	        Pedido pedido = null;
	        pedido = pedidoService.getPedido(id);
			return pedido;
	 }



	 @RequestMapping(value = "/pedido", method = RequestMethod.POST)
	    public ResponseEntity<Void> createPedido(@RequestBody Pedido pedido,    UriComponentsBuilder ucBuilder) {
	   
	        pedidoService.addPedido(pedido);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/pedido/{id}").buildAndExpand(pedido.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
		
	 @RequestMapping(value = "/pedido/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Pedido> actualizarPedido(@PathVariable("id") int id, @RequestBody Pedido pedido) {
	        
	        
	        Pedido pedidoFound = pedidoService.getPedido(id);
	         
	        if (pedidoFound==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
	        }
	 
				pedidoFound.setEstatus(pedido.getEstatus());
				pedidoFound.setNumero(pedido.getNumero());
				pedidoFound.setFecha(pedido.getFecha());
				pedidoFound.setAlmacen(pedido.getAlmacen());
				pedidoFound.setCliente_id(pedido.getCliente_id());
				pedidoFound.setCliente(pedido.getCliente());
			pedido.setId(null);
	        
	        pedidoService.editPedido(pedidoFound);
	        return new ResponseEntity<Pedido>(pedidoFound, HttpStatus.OK);
	  } 	
	
		
		@RequestMapping(value = "/pedido/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Pedido> deletePedido(@PathVariable("id") int id) {
	    	 System.out.println("Fetching & Deleting User with id " + id);
			 long rows = 0;	
	    	 
	         Pedido pedido = pedidoService.getPedido(id);
	         if (pedido == null) {
	             System.out.println("Unable to delete. Cuenta with id " + id + " not found");
	             return new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
	         }
	  
             if (rows==0){
	             pedidoService.deletePedido(pedido);
            	 return new ResponseEntity<Pedido>(HttpStatus.OK);
             } else {
            	 return new ResponseEntity<Pedido>(HttpStatus.PRECONDITION_FAILED);
             }
			
		}


	@RequestMapping(value = "/savePedido", method = RequestMethod.POST)
	public @ResponseBody String savePedido(
			@ModelAttribute("command") PedidoBean pedidoBean) {


		Pedido pedido = prepareModel(pedidoBean);
		pedidoService.addPedido(pedido);

		return "SUCCESS";
	}
	
	@RequestMapping(value = "/editPedido", method = RequestMethod.POST)
	public @ResponseBody String editPedido(
			@ModelAttribute("command") PedidoBean pedidoBean) {


		Pedido pedido = prepareModel(pedidoBean);
		pedidoService.editPedido(pedido);
		return "SUCCESS";
	}

	@RequestMapping(value = "/searchPedido", method = RequestMethod.GET)
	public ModelAndView addPedido(
			@ModelAttribute("command") PedidoBean pedidoBean,
			BindingResult result) {

		Map<String, Object> model = new HashMap<String, Object>();
		Pedido pedido = null;
		if (pedidoBean != null)
			pedido = prepareModel(pedidoBean);
		model.put("pedidos",
				prepareListofBean(pedidoService.listPedidos(pedido)));
		return new ModelAndView("searchPedido", model);
	}

	@RequestMapping(value = "/deletePedido", method = RequestMethod.POST)
	public ModelAndView deletePedido(
			@ModelAttribute("command") PedidoBean pedidoBean,
			BindingResult result) {
		System.out.println("delete " + pedidoBean.getId());
		pedidoService.deletePedido(prepareModel(pedidoBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pedido", null);
		model.put("pedidos",
				prepareListofBean(pedidoService.listPedidos(null)));
		return new ModelAndView("searchPedido", model);
	}

	@RequestMapping(value = "/entryPedido", method = RequestMethod.GET)
	public ModelAndView entryPedido() {
		return new ModelAndView("redirect:/searchPedido");
	}

	private Pedido prepareModel(PedidoBean pedidoBean) {
		Pedido pedido = new Pedido();

		pedido.setEstatus(pedidoBean.getEstatus());
		pedido.setNumero(pedidoBean.getNumero());
		pedido.setFecha(pedidoBean.getFecha());
		pedido.setAlmacen(pedidoBean.getAlmacen());
		pedido.setCliente_id(pedidoBean.getCliente_id());
		pedido.setCliente(pedidoBean.getCliente());
		pedido.setId(pedidoBean.getId());
		pedidoBean.setId(null);
		return pedido;
	}

	private List<PedidoBean> prepareListofBean(List<Pedido> pedidos) {
		List<PedidoBean> beans = null;
		if (pedidos != null && !pedidos.isEmpty()) {
			beans = new ArrayList<PedidoBean>();
			PedidoBean bean = null;
			for (Pedido pedido : pedidos) {
				bean = new PedidoBean();

                bean.setEstatus(pedido.getEstatus());
                bean.setNumero(pedido.getNumero());
                bean.setFecha(pedido.getFecha());
                bean.setAlmacen(pedido.getAlmacen());
                bean.setCliente_id(pedido.getCliente_id());
                bean.setCliente(pedido.getCliente());
				bean.setId(pedido.getId());
				beans.add(bean);
			}
		}
		return beans;
	}

}


