package com.salaobeleza.produtos.mapper;

import com.salaobeleza.produtos.dtos.FechamentoCompraResponse;
import com.salaobeleza.produtos.dtos.ProdutoRequest;
import com.salaobeleza.produtos.dtos.ProdutoResponse;
import com.salaobeleza.produtos.entities.Produto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toProduto(ProdutoRequest request) {
        return Produto.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .marca(request.marca())
                .tipo(request.tipo())
                .quantidadeDisponivel(request.quantidade())
                .preco(request.preco())
                .build();
    }

    public ProdutoResponse toProdutoResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getMarca(),
                produto.getTipo(),
                produto.getQuantidadeDisponivel(),
                produto.getPreco()
        );
    }

    public FechamentoCompraResponse toFechamentoProdutoResponse(Produto produto, double quantidade) {
        return new FechamentoCompraResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getMarca(),
                produto.getPreco(),
                quantidade
        );
    }
}
