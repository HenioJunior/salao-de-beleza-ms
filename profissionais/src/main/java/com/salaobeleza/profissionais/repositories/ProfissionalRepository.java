package com.salaobeleza.profissionais.repositories;

import com.salaobeleza.profissionais.entities.Profissional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfissionalRepository extends MongoRepository<Profissional, String> {
}
