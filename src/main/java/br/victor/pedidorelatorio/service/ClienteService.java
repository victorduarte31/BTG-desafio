package br.victor.pedidorelatorio.service;

import br.victor.pedidorelatorio.entity.Cliente;

public interface ClienteService {

   void criarOuAtualizarCliente(Long codigoCliente, Long pedido);

   Cliente getClienteById(Long codigoCliente);
}
