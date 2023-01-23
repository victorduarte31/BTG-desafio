package br.victor.pedidorelatorio.repository;

import br.victor.pedidorelatorio.entity.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PedidoRepository extends MongoRepository<Pedido, Long> {
}
