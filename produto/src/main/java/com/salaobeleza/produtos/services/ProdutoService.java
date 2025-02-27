package com.salaobeleza.produtos.services;

import com.salaobeleza.produtos.dtos.FechamentoCompraRequest;
import com.salaobeleza.produtos.dtos.FechamentoCompraResponse;
import com.salaobeleza.produtos.dtos.ProdutoRequest;
import com.salaobeleza.produtos.dtos.ProdutoResponse;
import com.salaobeleza.produtos.entities.Produto;
import com.salaobeleza.produtos.exceptions.FechamentoCompraException;
import com.salaobeleza.produtos.mapper.ProdutoMapper;
import com.salaobeleza.produtos.repositories.ProdutoRepository;
import com.salaobeleza.produtos.exceptions.ResourceNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
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

    public List<FechamentoCompraResponse> compraProdutos(List<FechamentoCompraRequest> request) {

        //produtos disponiveis
        List<String> produtosIds = request
                .stream()
                .map(FechamentoCompraRequest::produtoId)
                .toList();

        //busca produtos em estoque
        List<Produto> produtosArmazenados = repository.findAllByIdInOrderById(produtosIds);
        if(produtosIds.size() != produtosArmazenados.size()) {
            throw new FechamentoCompraException("One or more products does not exists");
        }

        List<FechamentoCompraRequest> requisicoesArmazenadas = request
                .stream()
                .sorted(Comparator.comparing(FechamentoCompraRequest::produtoId))
                .toList();

        //comprando diferentes produtos
        List<FechamentoCompraResponse> produtosComprados = new ArrayList<>();

        for (int i=0; i < produtosArmazenados.size(); i++) {
            Produto produto = produtosArmazenados.get(i);
            FechamentoCompraRequest requisicaoProduto = requisicoesArmazenadas.get(i);
            if(produto.getQuantidadeDisponivel() < requisicaoProduto.quantidade()) {
                throw  new FechamentoCompraException("Estoque insuficiente para o produto com ID: "
                        + produto.getId());
            }
            produto.setQuantidadeDisponivel(produto.getQuantidadeDisponivel() - requisicaoProduto.quantidade());
            repository.save(produto);
            produtosComprados.add(mapper.toFechamentoProdutoResponse(produto, requisicaoProduto.quantidade()));
        }

        return produtosComprados;
    }

    public void atualizaProduto(ProdutoRequest request) {
        var produto = repository.findById(request.id())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Produto com id '%s' não encontrado", request.id())));
        mergeProduto(produto, request);
        repository.save(produto);
    }

    private void mergeProduto(Produto produto, ProdutoRequest request) {

        if(StringUtils.isNotBlank(request.nome())) {
            produto.setNome(request.nome());
        }

        if(StringUtils.isNotBlank(request.descricao())) {
            produto.setNome(request.descricao());
        }

        if(StringUtils.isNotBlank(request.marca())) {
            produto.setNome(request.marca());
        }

        if(StringUtils.isNotBlank(request.tipo().toString())) {
            produto.setTipo(request.tipo());
        }

        if (!request.preco().equals(0)) {
            produto.setPreco(request.preco());
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
