package com.salaobeleza.servico.services;

import com.salaobeleza.servico.dtos.ServicoRequest;
import com.salaobeleza.servico.dtos.ServicoResponse;
import com.salaobeleza.servico.entities.Servico;
import com.salaobeleza.servico.mapper.ServicoMapper;
import com.salaobeleza.servico.repositories.ServicoRepository;
import com.salaobeleza.servico.services.exceptions.ResourceNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;


    @Autowired
    private ServicoMapper mapper;

    public String novoServico(ServicoRequest request) {
        var servico = repository.save(mapper.toServico(request));
        return servico.getId();
    }

    public void atualizaServico(ServicoRequest request) {
        var servico = repository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Servico com id '%s' não encontrado", request.getId())));
        mergeServico(servico, request);
        repository.save(servico);
    }

    private void mergeServico(Servico servico, ServicoRequest request) {
        if(StringUtils.isNotBlank(request.getTipo().toString())) {
            servico.setTipo(request.getTipo());
        }
        if(request.getValor() != 0) {
            servico.setValor(request.getValor());
        }
    }

    @Transactional(readOnly = true)
    public ServicoResponse buscaPorId(String id) {
        return repository.findById(id)
                .map(mapper::toServicoResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Servico com id '%s' não encontrado", id)));
    }

    @Transactional(readOnly = true)
    public List<ServicoResponse> buscaTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toServicoResponse)
                .collect(Collectors.toList());
    }


    public void excluiServico(String id) {
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

