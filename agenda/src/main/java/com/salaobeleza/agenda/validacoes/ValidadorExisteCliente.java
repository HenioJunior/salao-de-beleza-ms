package com.salaobeleza.agenda.validacoes;

import com.salaobeleza.agenda.dtos.DadosAgendamentoServico;
import com.salaobeleza.agenda.exceptions.ValidacaoException;
import com.salaobeleza.agenda.http.ApiCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ValidadorExisteCliente implements ValidadorAgendamento {

    @Autowired
    private ApiCliente apiCliente;

    public void validar(DadosAgendamentoServico dados) {
       var cliente = apiCliente.getCliente(dados.clienteId());
        Assert.notNull(cliente, "Cliente não cadastrado!");
    }
}

