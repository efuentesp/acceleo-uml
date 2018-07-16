
package com.softtek.acceleo.demo.wizard.repository;

import java.util.List;
import com.softtek.acceleo.demo.wizard.domain.Pedido;

public interface PedidoRepository {

	
	 public void addPedido(Pedido pedido);   
	 
	 public void editPedido(Pedido pedido);
	   
	 public List<Pedido> listPedidos(Pedido pedido);   
	    
	 public Pedido getPedido(int empid);   
	    
	 public void deletePedido(Pedido pedido); 

	 public List<Pedido> listPedidosQuery(Pedido pedido, String query, int page, int size);

	 public List<Pedido> listPedidosPagination(Pedido pedido, int page, int size);	

     public long getTotalRows();

     public long getTotalRows(String query);

     public long getTotalRowsSearch(String query);

}

