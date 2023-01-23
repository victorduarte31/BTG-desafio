package br.victor.pedidorelatorio.service.impl;

import br.victor.pedidorelatorio.entity.Item;
import br.victor.pedidorelatorio.entity.Pedido;
import br.victor.pedidorelatorio.exception.PedidoRelatorioException;
import br.victor.pedidorelatorio.repository.PedidoRepository;
import br.victor.pedidorelatorio.request.PedidoRequest;
import br.victor.pedidorelatorio.service.ClienteService;
import br.victor.pedidorelatorio.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final MongoTemplate mongoTemplate;
    private final Validator validator;
    private final ClienteService clienteService;

    @Override
    public void criarPedidoECliente(PedidoRequest pedidoRequest) {

        try {
            StringBuilder sb = new StringBuilder();

            Set<ConstraintViolation<PedidoRequest>> violations = validator.validate(pedidoRequest);

            if (!violations.isEmpty()) {
                for (ConstraintViolation<PedidoRequest> violation : violations) {
                    sb.append(violation.getMessage());
                }
                log.error("Erro na validação do request recebido ", sb);
                throw new PedidoRelatorioException(sb.toString());
            }

            clienteService.criarOuAtualizarCliente(pedidoRequest.getCodigoCliente(), 1L);
            criarPedido(pedidoRequest);


        } catch (Exception e) {
            throw new PedidoRelatorioException(e.getMessage());
        }
    }

    @Override
    public BigDecimal buscarValorTotalPedido(Long numeroPedido) {

        Pedido pedido = pedidoRepository.findById(numeroPedido).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return calcularValorTotal(pedido.getItens());
    }

    @Override
    public List<Pedido> getTodosPedidosPorCliente(Long codigoCliente) {

        Query query = new Query();
        query.addCriteria(Criteria.where("cliente.id").is(codigoCliente));
        List<Pedido> pedidos = mongoTemplate.find(query, Pedido.class);

        if (pedidos.isEmpty()) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.");
        }

        return pedidos;
    }


    public void criarPedido(PedidoRequest pedidoRequest) {

        Pedido pedido = Pedido.builder()
                .valorTotalPedido(calcularValorTotal(pedidoRequest.getItens()))
                .id(pedidoRequest.getCodigoPedido())
                .cliente(clienteService.getClienteById(pedidoRequest.getCodigoCliente()))
                .itens(pedidoRequest.getItens())
                .build();

        pedidoRepository.save(pedido);
    }

    private BigDecimal calcularValorTotal(List<Item> itens) {

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (Item item : itens) {
            BigDecimal valor = item.getPreco().multiply(new BigDecimal(item.getQuantidade()));
            valorTotal = valorTotal.add(valor);
        }
        return valorTotal;
    }
}
