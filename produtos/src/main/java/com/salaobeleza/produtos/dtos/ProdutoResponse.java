package com.salaobeleza.produtos.dtos;

import com.salaobeleza.produtos.enums.TipoProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProdutoResponse {

    private String id;
    private String nome;
    private String descricao;
    private String marca;
    private TipoProduto tipo;
}
