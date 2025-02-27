package com.salaobeleza.agenda.repositories;

import com.salaobeleza.agenda.entities.Agenda;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface AgendaRepository extends MongoRepository<Agenda, String> {
    boolean existsByProfissionalIdAndHorario(String s, LocalDateTime horario);

    boolean existsByClienteIdAndHorarioBetween(String s, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByClienteIdAndHorario(String s, LocalDateTime horario);
}
