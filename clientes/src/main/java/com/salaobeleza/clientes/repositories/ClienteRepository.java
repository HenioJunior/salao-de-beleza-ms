package com.salaobeleza.clientes.repositories;


import com.salaobeleza.clientes.entites.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

}
