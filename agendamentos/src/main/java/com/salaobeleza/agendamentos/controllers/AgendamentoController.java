package com.salaobeleza.agendamentos.controllers;

import com.salaobeleza.agendamentos.dtos.AgendamentoRequest;
import com.salaobeleza.agendamentos.dtos.AgendamentoResponse;
import com.salaobeleza.agendamentos.services.AgendamentoService;
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
@Tag(name = "Agendamento de Serviços")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo agendamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    public ResponseEntity<String> novoAgendamento(@Valid @RequestBody AgendamentoRequest request) {
        return ResponseEntity.ok(service.novoAgendamento(request));
    }

    @PutMapping()
    @Operation(summary = "Modifica o cadastro de um agendamento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do agendamento atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do srofissional")
    })
    public ResponseEntity<Void> atualizaAgendamento(@Valid @RequestBody AgendamentoRequest request) {
        service.atualizaAgendamento(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Realiza a busca de um agendamento por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),

    })
    public ResponseEntity<AgendamentoResponse> findById(@PathVariable String id) {
        AgendamentoResponse  response =  service.buscaPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Realiza a busca de todos os profissionais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),

    })
    public ResponseEntity<List<AgendamentoResponse>>  findAll() {
        return ResponseEntity.ok(service.buscaTodos());
    }

    @DeleteMapping(value = {"/{id}"})
    @Operation(summary = "Remove um agendamento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o agendamento")
    })
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deletaAgendamento(id);
        return ResponseEntity.noContent().build();
    }


}