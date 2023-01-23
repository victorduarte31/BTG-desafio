package br.victor.pedidorelatorio.repository;

import br.victor.pedidorelatorio.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, Long> {
}
