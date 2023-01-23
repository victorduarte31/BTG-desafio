package br.victor.pedidorelatorio.service;

import br.victor.pedidorelatorio.entity.Pedido;
import br.victor.pedidorelatorio.request.PedidoRequest;

import java.math.BigDecimal;
import java.util.List;

public interface PedidoService {

    void criarPedidoECliente(PedidoRequest pedidoRequest);

    BigDecimal buscarValorTotalPedido(Long numeroPedido);

    List<Pedido> getTodosPedidosPorCliente(Long codigoCliente);
}
