package br.victor.pedidorelatorio.service;

import br.victor.pedidorelatorio.entity.Cliente;
import br.victor.pedidorelatorio.repository.ClienteRepository;
import br.victor.pedidorelatorio.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteServiceImpl clienteService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCriarCliente() {

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        when(clienteRepository.save(any())).thenReturn(new Cliente());
        clienteService.criarOuAtualizarCliente(1L, 1L);

    }

    @Test
    void testAtualizarCliente() {

        Cliente cliente = new Cliente();
        cliente.setPedidos(2L);

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any())).thenReturn(new Cliente());
        clienteService.criarOuAtualizarCliente(1L, 1L);

    }

    @Test
    @DisplayName("buscando cliente por id")
    void testGetClienteById() {

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Cliente()));
        Cliente cliente = clienteService.getClienteById(1L);

        Assertions.assertNotNull(cliente);


    }

}
