package com.salaobeleza.clientes.services;


import com.salaobeleza.clientes.dtos.ClienteRequest;
import com.salaobeleza.clientes.dtos.ClienteResponse;
import com.salaobeleza.clientes.entites.Cliente;
import com.salaobeleza.clientes.mapper.ClienteMapper;
import com.salaobeleza.clientes.repositories.ClienteRepository;
import com.salaobeleza.clientes.services.exceptions.DatabaseException;
import com.salaobeleza.clientes.services.exceptions.ResourceNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteMapper mapper;

    public String novoCliente(ClienteRequest request) {
        var cliente = repository.save(mapper.toCliente(request));
        return cliente.getId();
    }

    public void atualizaCliente(ClienteRequest request) {
        var cliente = repository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Cliente com id '%s' não encontrado", request.getId())));
        mergeCliente(cliente, request);
        repository.save(cliente);
    }

    private void mergeCliente(Cliente cliente, ClienteRequest request) {
        if(StringUtils.isNotBlank(request.getNome())) {
            cliente.setNome(request.getNome());
        }
        if(StringUtils.isNotBlank(request.getCpf())) {
            cliente.setCpf(request.getCpf());
        }
        if(StringUtils.isNotBlank(request.getEmail())) {
            cliente.setEmail(request.getEmail());
        }
        if(StringUtils.isNotBlank(request.getTelefone())) {
            cliente.setTelefone(request.getTelefone());
        }
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscaPorId(String id) {
        return repository.findById(id)
                .map(mapper::toClienteResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Cliente com id '%s' não encontrado", id)));
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> buscaTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toClienteResponse)
                .collect(Collectors.toList());
    }

    public void deletaCliente(String id) {
        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DatabaseException e) {
            throw new RuntimeException("Falha de integridade referencial");
        }
    }
}

