package br.victor.pedidorelatorio.controller;

import br.victor.pedidorelatorio.entity.Pedido;
import br.victor.pedidorelatorio.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/pedido")
@RequiredArgsConstructor
@Slf4j
public class PedidoController {

    private final PedidoService pedidoService;


    @GetMapping("numero-pedido/{numeroPedido}")
    @Operation(summary = "Retorna o valor total de um pedido")
    public BigDecimal getValorTotalPedido(@PathVariable Long numeroPedido) {
        return pedidoService.buscarValorTotalPedido(numeroPedido);
    }


    @GetMapping("codigo-cliente/{codigoCliente}")
    @Operation(summary = "Retorna todos os pedidos do cliente")
    public List<Pedido> getTotalPedidosPorCliente(@PathVariable Long codigoCliente) {
        return pedidoService.getTodosPedidosPorCliente(codigoCliente);
    }

}
