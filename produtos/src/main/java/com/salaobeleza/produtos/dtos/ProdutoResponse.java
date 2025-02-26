package com.salaobeleza.produtos.dtos;

import com.salaobeleza.produtos.enums.TipoProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

public record ProdutoResponse(
        String id,
        String nome,
        String descricao,
        String marca,
        TipoProduto tipo,
        double quantidade,
        BigDecimal preco
) { }
