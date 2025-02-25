package com.salaobeleza.produtos.entities;

import com.salaobeleza.produtos.enums.TipoProduto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "produtos")
public class Produto {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private String marca;
    private TipoProduto tipo;
}
