package com.salaobeleza.servico.repositories;

import com.salaobeleza.servico.entities.Servico;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServicoRepository extends MongoRepository<Servico, String> {

}
