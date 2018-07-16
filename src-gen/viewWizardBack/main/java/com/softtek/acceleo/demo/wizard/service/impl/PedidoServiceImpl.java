

package com.softtek.acceleo.demo.wizard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.acceleo.demo.wizard.repository.PedidoRepository;
import com.softtek.acceleo.demo.wizard.domain.Pedido;
import com.softtek.acceleo.demo.wizard.service.PedidoService;

@Service("pedidoService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addPedido(Pedido pedido) {
		
		pedidoRepository.addPedido(pedido);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editPedido(Pedido pedido) {
		
		pedidoRepository.editPedido(pedido);
	}
	
	
	public List<Pedido> listPedidos(Pedido pedido) {

		return pedidoRepository.listPedidos(pedido);
	}

	public Pedido getPedido(int empid) {

		return pedidoRepository.getPedido(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deletePedido(Pedido pedido) {
		System.out.println("Entrando al deletePedido");

		 pedidoRepository.deletePedido(pedido);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Pedido> listPedidosPagination(Pedido pedido, int page, int size) {

		return pedidoRepository.listPedidosPagination(pedido, page, size);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRowsSearch(String query) {

		return pedidoRepository.getTotalRowsSearch(query);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows(String query) {

		return pedidoRepository.getTotalRows(query);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows() {

		return pedidoRepository.getTotalRows();
	}

	public List<Pedido> listPedidosQuery(Pedido pedido, String query, int page, int size) {
		// TODO Auto-generated method stub
		return pedidoRepository.listPedidosQuery(pedido, query, page, size);
	}

}

