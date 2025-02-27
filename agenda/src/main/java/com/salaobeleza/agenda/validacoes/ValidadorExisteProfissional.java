package com.salaobeleza.agenda.validacoes;

import com.salaobeleza.agenda.dtos.DadosAgendamentoServico;
import com.salaobeleza.agenda.exceptions.ValidacaoException;
import com.salaobeleza.agenda.http.ApiProfissional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ValidadorExisteProfissional implements ValidadorAgendamento {

    @Autowired
    private ApiProfissional apiProfissional;

    public void validar(DadosAgendamentoServico dados) {
        var profissional = apiProfissional.getProfissional(dados.profissionalId());
        Assert.notNull(profissional, "Profissional não cadastrado");
    }
}
