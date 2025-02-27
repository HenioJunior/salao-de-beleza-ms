package com.salaobeleza.servico.mapper;

import com.salaobeleza.servico.dtos.ServicoRequest;
import com.salaobeleza.servico.dtos.ServicoResponse;
import com.salaobeleza.servico.entities.Servico;
import org.springframework.stereotype.Component;

@Component
public class ServicoMapper {

    public Servico toServico(ServicoRequest request) {
        return Servico.builder()
                .valor(request.getValor())
                .tipo(request.getTipo())
                .build();
    }

    public ServicoResponse toServicoResponse(Servico servico) {
        return new ServicoResponse(
                servico.getId(),
                servico.getValor(),
                servico.getTipo()
        );
    }
}
