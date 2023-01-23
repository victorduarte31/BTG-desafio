package br.victor.pedidorelatorio.service;

import br.victor.pedidorelatorio.entity.Item;
import br.victor.pedidorelatorio.entity.Pedido;
import br.victor.pedidorelatorio.repository.PedidoRepository;
import br.victor.pedidorelatorio.request.PedidoRequest;
import br.victor.pedidorelatorio.service.impl.PedidoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private MongoTemplate mongoTemplate;
    @Mock
    private Validator validator;
    @Mock
    private ClienteService clienteService;
    @InjectMocks
    private PedidoServiceImpl pedidoService;

    public PedidoServiceImplTest() {
    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("teste contrato invalido")
    void testContratoInvalido() {
        Item item = new Item();
        item.setPreco(BigDecimal.TEN);
        item.setQuantidade(2);
        item.setProduto("Livro");

        PedidoRequest request = new PedidoRequest();
        request.setItens(List.of(item));

        doNothing().when(clienteService).criarOuAtualizarCliente(anyLong(), anyLong());
        when(pedidoRepository.save(any())).thenReturn(new Pedido());

        pedidoService.criarPedidoECliente(request);

    }

    @Test
    @DisplayName("criando pedido e cliente")
    void testCriarPedidoECliente() {
        Item item = new Item();
        item.setPreco(BigDecimal.TEN);
        item.setQuantidade(2);
        item.setProduto("Livro");

        PedidoRequest request = new PedidoRequest();
        request.setCodigoPedido(1L);
        request.setCodigoCliente(1L);
        request.setItens(List.of(item));

        doNothing().when(clienteService).criarOuAtualizarCliente(anyLong(), anyLong());
        when(pedidoRepository.save(any())).thenReturn(new Pedido());

        pedidoService.criarPedidoECliente(request);

    }

    @Test
    @DisplayName("Buscando valor total do pedido por pedido")
    void testBuscarValorTotalPedido() {

        Item item = new Item();
        item.setPreco(BigDecimal.TEN);
        item.setQuantidade(2);
        item.setProduto("Livro");

        Pedido pedido = new Pedido();
        pedido.setItens(List.of(item));

        when(pedidoRepository.findById(anyLong())).thenReturn(Optional.of(pedido));

        Assertions.assertNotNull(pedidoService.buscarValorTotalPedido(2L));


    }

    @Test
    @DisplayName("busca todos os pedidos por cliente")
    void testGetTodosPedidosPorCliente() {
        Item item = new Item();
        item.setPreco(BigDecimal.TEN);
        item.setQuantidade(2);
        item.setProduto("Livro");

        Pedido pedido = new Pedido();
        pedido.setItens(List.of(item));


        when(mongoTemplate.find(any(), any())).thenReturn(List.of(pedido));

        pedidoService.getTodosPedidosPorCliente(1L);
    }

}
