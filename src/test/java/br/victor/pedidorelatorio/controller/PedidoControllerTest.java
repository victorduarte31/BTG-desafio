package br.victor.pedidorelatorio.controller;

import br.victor.pedidorelatorio.entity.Pedido;
import br.victor.pedidorelatorio.service.PedidoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

public class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Teste pegando valor total do pedido")
    void testGetValorTotalPedido() {
        when(pedidoService.buscarValorTotalPedido(anyLong())).thenReturn(new BigDecimal("50.0"));
        Assertions.assertNotNull(pedidoController.getValorTotalPedido(2L));
    }



    @Test
    @DisplayName("Teste retornando uma lista com todos os pedidos do cliente")
    void testGetTotalPedidosPorCliente() {
        when(pedidoService.getTodosPedidosPorCliente(anyLong())).thenReturn(List.of(new Pedido()));
        Assertions.assertNotNull(pedidoController.getTotalPedidosPorCliente(2L));

    }
}
