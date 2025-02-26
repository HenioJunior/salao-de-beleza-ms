package com.salaobeleza.produtos.dtos;

import com.salaobeleza.produtos.enums.TipoProduto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public record ProdutoRequest(
        String id,
        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotBlank
        String marca,
        TipoProduto tipo,
        double quantidade,
        BigDecimal preco
)
{ }
