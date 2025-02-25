package com.salaobeleza.servicos.dtos;

import com.salaobeleza.servicos.enums.TipoServico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Valid
public class ServicoRequest {

    private String id;
    @NotNull
    private double valor;
    @NotNull
    private TipoServico tipo;
}
