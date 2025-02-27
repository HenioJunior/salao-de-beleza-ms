package com.salaobeleza.agenda.validacoes;

import com.salaobeleza.agenda.dtos.DadosAgendamentoServico;

public interface ValidadorAgendamento {

    void validar(DadosAgendamentoServico dados);
}
