package com.salaobeleza.produtos.repositories;

import com.salaobeleza.produtos.entities.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
}
