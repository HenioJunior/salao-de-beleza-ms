package com.salaobeleza.produtos.dtos;

import jakarta.validation.constraints.NotNull;

public record FechamentoCompraRequest(
        @NotNull(message = "O id do produto é obrigatório")
        String produtoId,
        @NotNull(message = "A quantidade do produto é obrigatório")
        double quantidade
) {
}
