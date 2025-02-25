package com.salaobeleza.produtos.controllers;

import com.salaobeleza.produtos.dtos.ProdutoRequest;
import com.salaobeleza.produtos.dtos.ProdutoResponse;
import com.salaobeleza.produtos.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
@Tag(name = "Produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    public ResponseEntity<String> novoProduto(@Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.ok(service.novoProduto(request));
    }

    @PutMapping()
    @Operation(summary = "Modifica o cadastro de um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do produto atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do produto")
    })
    public ResponseEntity<Void> atualizaProduto(@Valid @RequestBody ProdutoRequest request) {
        service.atualizaProduto(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Realiza a busca de um produto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),

    })
    public ResponseEntity<ProdutoResponse> findById(@PathVariable String id) {
        ProdutoResponse  response =  service.buscaPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Realiza a busca de todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),

    })
    public ResponseEntity<List<ProdutoResponse>>  findAll() {
        return ResponseEntity.ok(service.buscaTodos());
    }

    @DeleteMapping(value = {"/{id}"})
    @Operation(summary = "Remove um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o produto")
    })
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deletaProduto(id);
        return ResponseEntity.noContent().build();
    }
}
