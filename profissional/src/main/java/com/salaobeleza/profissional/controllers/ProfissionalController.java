package com.salaobeleza.profissional.controllers;

import com.salaobeleza.profissional.dtos.ProfissionalRequest;
import com.salaobeleza.profissional.dtos.ProfissionalResponse;
import com.salaobeleza.profissional.services.ProfissionalService;
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
@Tag(name = "Profissional")
public class ProfissionalController {

    @Autowired
    private ProfissionalService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo profissional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profissional cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    public ResponseEntity<String> novoProfissional(@Valid @RequestBody ProfissionalRequest request) {
        return ResponseEntity.ok(service.novoProfissional(request));
    }

    @PutMapping()
    @Operation(summary = "Modifica o cadastro de um profissional existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do profissional atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do profissional")
    })
    public ResponseEntity<Void> atualizaProfissional(@Valid @RequestBody ProfissionalRequest request) {
        service.atualizaProfissional(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Realiza a busca de um profissional por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),

    })
    public ResponseEntity<ProfissionalResponse> findById(@PathVariable String id) {
        ProfissionalResponse  response =  service.buscaPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Realiza a busca de todos os profissionais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),

    })
    public ResponseEntity<List<ProfissionalResponse>>  findAll() {
        return ResponseEntity.ok(service.buscaTodos());
    }

    @DeleteMapping(value = {"/{id}"})
    @Operation(summary = "Remove um profissional existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profissional removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o profissional")
    })
    public ResponseEntity<Void> excluiProfissional(@PathVariable String id) {
        service.excluiProfissional(id);
        return ResponseEntity.noContent().build();
    }
}
