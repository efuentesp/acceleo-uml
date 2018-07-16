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


import com.softtek.acceleo.demo.catalogo.bean.ArticuloBean;
import com.softtek.acceleo.demo.domain.Articulo;
import com.softtek.acceleo.demo.service.ArticuloService;

@Controller
public class ArticuloController {

	@Autowired
	private ArticuloService articuloService;
	
	Articulo articulo = new Articulo();

	@RequestMapping(value = "/articulo", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody  List<Articulo> getArticulos(@RequestParam Map<String,String> requestParams, HttpServletRequest request, HttpServletResponse response) {

       	String query=requestParams.get("q");
		int _page= requestParams.get("_page")==null?0:new Integer(requestParams.get("_page")).intValue();
		long rows = 0;

		

		List<Articulo> listArticulo = null;

		if (query==null && _page == 0 ) {
       		listArticulo = articuloService.listArticuloss(articulo);
			rows = articuloService.getTotalRows();
		} else if (query!= null){
				listArticulo = articuloService.listArticulossQuery(articulo, query, _page, 10);
				rows = articuloService.getTotalRowsSearch(query);
			
		} else if (_page != 0){
			listArticulo = articuloService.listArticulosPagination(articulo, _page, 10);
			rows = articuloService.getTotalRows();
		} 	

		response.setHeader("Access-Control-Expose-Headers", "x-total-count");
		response.setHeader("x-total-count", String.valueOf(rows).toString());	

		return listArticulo;
	}
	
	@RequestMapping(value = "/articulo/{id}", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody  Articulo getArticulo(@PathVariable("id") int id) {
	        
	        articulo.setId(id);
	        
	        Articulo articulo = null;
	        articulo = articuloService.getArticulo(id);
			return articulo;
	 }



	 @RequestMapping(value = "/articulo", method = RequestMethod.POST)
	    public ResponseEntity<Void> createArticulo(@RequestBody Articulo articulo,    UriComponentsBuilder ucBuilder) {
	   
	        articuloService.addArticulo(articulo);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/articulo/{id}").buildAndExpand(articulo.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
		
	 @RequestMapping(value = "/articulo/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Articulo> actualizarArticulo(@PathVariable("id") int id, @RequestBody Articulo articulo) {
	        
	        
	        Articulo articuloFound = articuloService.getArticulo(id);
	         
	        if (articuloFound==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Articulo>(HttpStatus.NOT_FOUND);
	        }
	 
				articuloFound.setCategoria(articulo.getCategoria());
				articuloFound.setDescripcion(articulo.getDescripcion());
				articuloFound.setPrecio(articulo.getPrecio());
				articuloFound.setCantidad(articulo.getCantidad());
			articulo.setId(null);
	        
	        articuloService.editArticulo(articuloFound);
	        return new ResponseEntity<Articulo>(articuloFound, HttpStatus.OK);
	  } 	
	
		
		@RequestMapping(value = "/articulo/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Articulo> deleteArticulo(@PathVariable("id") int id) {
	    	 System.out.println("Fetching & Deleting User with id " + id);
			 long rows = 0;	
	    	 
	         Articulo articulo = articuloService.getArticulo(id);
	         if (articulo == null) {
	             System.out.println("Unable to delete. Cuenta with id " + id + " not found");
	             return new ResponseEntity<Articulo>(HttpStatus.NOT_FOUND);
	         }
	  
             

             if (rows==0){
	             articuloService.deleteArticulo(articulo);
            	 return new ResponseEntity<Articulo>(HttpStatus.OK);
             } else {
            	 return new ResponseEntity<Articulo>(HttpStatus.PRECONDITION_FAILED);
             }
			
		}


	@RequestMapping(value = "/saveArticulo", method = RequestMethod.POST)
	public @ResponseBody String saveArticulo(
			@ModelAttribute("command") ArticuloBean articuloBean) {


		Articulo articulo = prepareModel(articuloBean);
		articuloService.addArticulo(articulo);

		return "SUCCESS";
	}
	
	@RequestMapping(value = "/editArticulo", method = RequestMethod.POST)
	public @ResponseBody String editArticulo(
			@ModelAttribute("command") ArticuloBean articuloBean) {


		Articulo articulo = prepareModel(articuloBean);
		articuloService.editArticulo(articulo);
		return "SUCCESS";
	}

	@RequestMapping(value = "/searchArticulo", method = RequestMethod.GET)
	public ModelAndView addArticulo(
			@ModelAttribute("command") ArticuloBean articuloBean,
			BindingResult result) {

		Map<String, Object> model = new HashMap<String, Object>();
		Articulo articulo = null;
		if (articuloBean != null)
			articulo = prepareModel(articuloBean);
		model.put("articulos",
				prepareListofBean(articuloService.listArticuloss(articulo)));
		return new ModelAndView("searchArticulo", model);
	}

	@RequestMapping(value = "/deleteArticulo", method = RequestMethod.POST)
	public ModelAndView deleteArticulo(
			@ModelAttribute("command") ArticuloBean articuloBean,
			BindingResult result) {
		System.out.println("delete " + articuloBean.getId());
		articuloService.deleteArticulo(prepareModel(articuloBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("articulo", null);
		model.put("articulos",
				prepareListofBean(articuloService.listArticuloss(null)));
		return new ModelAndView("searchArticulo", model);
	}

	@RequestMapping(value = "/entryArticulo", method = RequestMethod.GET)
	public ModelAndView entryArticulo() {
		return new ModelAndView("redirect:/searchArticulo");
	}

	private Articulo prepareModel(ArticuloBean articuloBean) {
		Articulo articulo = new Articulo();

		articulo.setCategoria(articuloBean.getCategoria());
		articulo.setDescripcion(articuloBean.getDescripcion());
		articulo.setPrecio(articuloBean.getPrecio());
		articulo.setCantidad(articuloBean.getCantidad());
		articulo.setId(articuloBean.getId());
		articuloBean.setId(null);
		return articulo;
	}

	private List<ArticuloBean> prepareListofBean(List<Articulo> articulos) {
		List<ArticuloBean> beans = null;
		if (articulos != null && !articulos.isEmpty()) {
			beans = new ArrayList<ArticuloBean>();
			ArticuloBean bean = null;
			for (Articulo articulo : articulos) {
				bean = new ArticuloBean();

                bean.setCategoria(articulo.getCategoria());
                bean.setDescripcion(articulo.getDescripcion());
                bean.setPrecio(articulo.getPrecio());
                bean.setCantidad(articulo.getCantidad());
				bean.setId(articulo.getId());
				beans.add(bean);
			}
		}
		return beans;
	}

}


