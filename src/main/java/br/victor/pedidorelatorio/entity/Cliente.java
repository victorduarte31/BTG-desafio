package br.victor.pedidorelatorio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tb_cliente")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    private Long id;
    private Long pedidos;


}
