package com.salaobeleza.produtos.dtos;

import java.math.BigDecimal;

public record FechamentoCompraResponse(
        String id,
        String nome,
        String descricao,
        String marca,
        BigDecimal preco,
        double quantidade
) {}
