package com.salaobeleza.agendamentos.repositories;

import com.salaobeleza.agendamentos.entities.Agendamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgendamentoRepository extends MongoRepository<Agendamento, String> {

}
