package com.salaobeleza.clientes.controllers;

import com.salaobeleza.clientes.dtos.ClienteRequest;
import com.salaobeleza.clientes.dtos.ClienteResponse;
import com.salaobeleza.clientes.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
@Tag(name = "Cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    public ResponseEntity<String> novoCliente(@Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.ok(service.novoCliente(request));
    }

    @PutMapping()
    @Operation(summary = "Modifica o cadastro de um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do cliente")
    })
    public ResponseEntity<Void> atualizaCliente(@Valid @RequestBody ClienteRequest request) {
        service.atualizaCliente(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Realiza a busca de um cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),

    })
    public ResponseEntity<ClienteResponse> findById(@PathVariable String id) {
        ClienteResponse  response =  service.buscaPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Realiza a busca de todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),

    })
    public ResponseEntity<List<ClienteResponse>>  findAll() {
        return ResponseEntity.ok(service.buscaTodos());
    }

    @DeleteMapping(value = {"/{id}"})
    @Operation(summary = "Remove um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o cliente")
    })
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deletaCliente(id);
        return ResponseEntity.noContent().build();
    }
}
