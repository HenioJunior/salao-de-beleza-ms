package com.salaobeleza.profissionais.mapper;

import com.salaobeleza.profissionais.dtos.ProfissionalRequest;
import com.salaobeleza.profissionais.dtos.ProfissionalResponse;
import com.salaobeleza.profissionais.entities.Profissional;
import org.springframework.stereotype.Component;

@Component
public class ProfissionalMapper {

    public Profissional toProfissional(ProfissionalRequest request) {
        return Profissional.builder()
                .nome(request.getNome())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .build();
    }

    public ProfissionalResponse toProfissionalResponse(Profissional profissional) {
        return new ProfissionalResponse(
                profissional.getId(),
                profissional.getNome(),
                profissional.getCpf(),
                profissional.getEmail(),
                profissional.getTelefone()
        );
    }
}
