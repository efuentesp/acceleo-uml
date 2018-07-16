package com.softtek.acceleo.demo.wizard.service;

import com.softtek.acceleo.demo.wizard.domain.Pedido;
import java.util.List;

public interface PedidoService {

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

