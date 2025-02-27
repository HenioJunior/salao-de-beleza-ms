package com.salaobeleza.agenda.validacoes;

import com.salaobeleza.agenda.dtos.DadosAgendamentoServico;
import com.salaobeleza.agenda.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoSalao implements ValidadorAgendamento {

    public void validar(DadosAgendamentoServico dados){
        var dataConsulta = dados.horario();
        var segunda = dataConsulta.getDayOfWeek().equals(DayOfWeek.MONDAY);
        var antesdaAberturaDaClinica = dataConsulta.getHour() < 9;
        var depoisEnceramentoClinica = dataConsulta.getHour() >= 18;

        if (segunda || antesdaAberturaDaClinica || depoisEnceramentoClinica) {
            throw new ValidacaoException("Agendamento fora do horário de funcionamento do salão");
        }
    }
}
