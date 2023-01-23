package br.victor.pedidorelatorio.service.impl;

import br.victor.pedidorelatorio.entity.Cliente;
import br.victor.pedidorelatorio.exception.PedidoRelatorioException;
import br.victor.pedidorelatorio.repository.ClienteRepository;
import br.victor.pedidorelatorio.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public void criarOuAtualizarCliente(Long codigoCliente, Long pedido) {

        Cliente cliente = clienteRepository.findById(codigoCliente).orElse(null);

        if (cliente == null) {
            clienteRepository.save(new Cliente(codigoCliente, pedido));
        } else {
            cliente.setPedidos(cliente.getPedidos() + 1);
            clienteRepository.save(cliente);
        }

    }

    @Override
    public Cliente getClienteById(Long codigoCliente) {
        return clienteRepository.findById(codigoCliente)
                .orElseThrow(() -> new PedidoRelatorioException("Cliente não encontrado..."));
    }
}
