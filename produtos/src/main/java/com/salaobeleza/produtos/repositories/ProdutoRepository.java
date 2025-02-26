package com.salaobeleza.produtos.repositories;

import com.salaobeleza.produtos.entities.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    List<Produto> findAllByIdInOrderById(List<String> ids);
}
