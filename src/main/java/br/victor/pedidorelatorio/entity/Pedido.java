package br.victor.pedidorelatorio.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document(collection = "tb_pedido")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    private Long id;
    private BigDecimal valorTotalPedido;
    @DBRef
    private Cliente cliente;
    private List<Item> itens;


}
