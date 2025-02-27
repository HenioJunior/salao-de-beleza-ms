package com.salaobeleza.profissional.repositories;

import com.salaobeleza.profissional.entities.Profissional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfissionalRepository extends MongoRepository<Profissional, String> {

}
