package com.salaobeleza.produtos.services;

import com.salaobeleza.produtos.dtos.ProdutoRequest;
import com.salaobeleza.produtos.dtos.ProdutoResponse;
import com.salaobeleza.produtos.entities.Produto;
import com.salaobeleza.produtos.mapper.ProdutoMapper;
import com.salaobeleza.produtos.repositories.ProdutoRepository;
import com.salaobeleza.produtos.services.exceptions.ResourceNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoMapper mapper;

    public String novoProduto(ProdutoRequest request) {
        var produto = repository.save(mapper.toProduto(request));
        return produto.getId();
    }

    public void atualizaProduto(ProdutoRequest request) {
        var produto = repository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Produto com id '%s' não encontrado", request.getId())));
        mergeProduto(produto, request);
        repository.save(produto);
    }

    private void mergeProduto(Produto produto, ProdutoRequest request) {
        if(StringUtils.isNotBlank(request.getTipo().toString())) {
            produto.setTipo(request.getTipo());
        }
        if(StringUtils.isNotBlank(request.getNome())) {
            produto.setNome(request.getNome());
        }
    }

    @Transactional(readOnly = true)
    public ProdutoResponse buscaPorId(String id) {
        return repository.findById(id)
                .map(mapper::toProdutoResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Produto com id '%s' não encontrado", id)));
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> buscaTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toProdutoResponse)
                .collect(Collectors.toList());
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletaProduto(String id) {
        if(!repository.existsById(id)) {
            throw new RuntimeException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Falha de integridade referencial");
        }
    }
}
