package br.victor.pedidorelatorio.amqp.consumer;

import br.victor.pedidorelatorio.exception.PedidoRelatorioException;
import br.victor.pedidorelatorio.request.PedidoRequest;
import br.victor.pedidorelatorio.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class QueueConsumer {

    private final PedidoService pedidoService;

    @RabbitListener(queues = "${queue.consumer}")
    public void recebendoMensagem(@Payload PedidoRequest pedidoRequest) {

        try {
            log.info("Recebendo mensagem {}", pedidoRequest);
            pedidoService.criarPedidoECliente(pedidoRequest);
        } catch (Exception e) {
            throw new PedidoRelatorioException(e.getMessage());
        }
    }
}
