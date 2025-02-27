package com.salaobeleza.agenda.validacoes;

import com.salaobeleza.agenda.dtos.DadosAgendamentoServico;
import com.salaobeleza.agenda.exceptions.ValidacaoException;
import com.salaobeleza.agenda.repositories.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteComOutroAgendamentoNoMesmoHorario implements ValidadorAgendamento {

    @Autowired
    private AgendaRepository repository;
    public void validar(DadosAgendamentoServico dados){
        var clientePossuiOutroServicoNoMesmoHorario = repository.existsByClienteIdAndHorario(dados.clienteId(), dados.horario());

        if(clientePossuiOutroServicoNoMesmoHorario) {
            throw new ValidacaoException("Cliente já possui outro servico agendado nesse mesmo horário");
        }
    }
}
