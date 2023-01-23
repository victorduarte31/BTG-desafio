package br.victor.pedidorelatorio.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tb_item")
public class Item {
    @NotNull
    private String produto;
    @NotNull
    private Integer quantidade;
    @NotNull
    private BigDecimal preco;

}
