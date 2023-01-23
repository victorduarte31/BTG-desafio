package br.victor.pedidorelatorio.request;

import br.victor.pedidorelatorio.entity.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {

    @NotNull
    private Long codigoPedido;
    @NotNull
    private Long codigoCliente;
    @NotNull
    private List<Item> itens;


}
