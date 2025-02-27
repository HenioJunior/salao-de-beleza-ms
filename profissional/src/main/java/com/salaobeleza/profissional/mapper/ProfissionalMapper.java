package com.salaobeleza.profissional.mapper;

import com.salaobeleza.profissional.dto.ServicoResponse;
import com.salaobeleza.profissional.dtos.ProfissionalRequest;
import com.salaobeleza.profissional.dtos.ProfissionalResponse;
import com.salaobeleza.profissional.entities.Profissional;
import com.salaobeleza.profissional.http.ApiServico;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfissionalMapper {

    @Autowired
    private ApiServico apiServico;

    public Profissional toProfissional(ProfissionalRequest request) {
        return Profissional.builder()
                .nome(request.getNome())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .servico(buscaServico(request.getServicoId()))
                .build();
    }

    public ProfissionalResponse toProfissionalResponse(Profissional profissional) {
        return new ProfissionalResponse(
                profissional.getId(),
                profissional.getNome(),
                profissional.getCpf(),
                profissional.getEmail(),
                profissional.getTelefone(),
                profissional.getServico()
        );
    }

    private ServicoResponse buscaServico(String id) {
        ServicoResponse servico = apiServico.getServico(id);
        return ServicoResponse.builder()
                .id(servico.getId())
                .tipo(servico.getTipo())
                .valor(servico.getValor())
                .build();
    }
}
