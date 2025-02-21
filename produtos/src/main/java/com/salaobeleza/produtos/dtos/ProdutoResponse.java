package com.salaobeleza.produtos.dtos;

import com.salaobeleza.produtos.entities.Produto;
import com.salaobeleza.produtos.enums.TipoProduto;

public class ProdutoResponse {

    private String id;
    private String nome;
    private String descricao;
    private String marca;
    private TipoProduto tipo;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.marca = produto.getMarca();
        this.tipo = produto.getTipo();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }
}
