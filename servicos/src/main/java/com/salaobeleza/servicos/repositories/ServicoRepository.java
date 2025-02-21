package com.salaobeleza.servicos.repositories;

import com.salaobeleza.servicos.entities.Servico;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServicoRepository extends MongoRepository<Servico, String> {

}
