package com.salaobeleza.servicos.mapper;

import com.salaobeleza.servicos.dtos.ServicoRequest;
import com.salaobeleza.servicos.dtos.ServicoResponse;
import com.salaobeleza.servicos.entities.Servico;
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
