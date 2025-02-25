package com.salaobeleza.servicos.controllers;

import com.salaobeleza.servicos.dtos.ServicoRequest;
import com.salaobeleza.servicos.dtos.ServicoResponse;
import com.salaobeleza.servicos.services.ServicoService;
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
@Tag(name = "Serviço")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo servico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servico cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    public ResponseEntity<String> novoServico(@Valid @RequestBody ServicoRequest request) {
        return ResponseEntity.ok(service.novoServico(request));
    }

    @PutMapping()
    @Operation(summary = "Modifica o cadastro de um servico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do servico atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do srofissional")
    })
    public ResponseEntity<Void> atualizaServico(@Valid @RequestBody ServicoRequest request) {
        service.atualizaServico(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Realiza a busca de um servico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),

    })
    public ResponseEntity<ServicoResponse> findById(@PathVariable String id) {
        ServicoResponse  response =  service.buscaPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Realiza a busca de todos os profissionais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),

    })
    public ResponseEntity<List<ServicoResponse>>  findAll() {
        return ResponseEntity.ok(service.buscaTodos());
    }

    @DeleteMapping(value = {"/{id}"})
    @Operation(summary = "Remove um servico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servico removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o servico")
    })
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deletaServico(id);
        return ResponseEntity.noContent().build();
    }
}
