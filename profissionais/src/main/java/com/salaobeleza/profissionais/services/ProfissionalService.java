package com.salaobeleza.profissionais.services;

import com.salaobeleza.profissionais.dtos.ProfissionalRequest;
import com.salaobeleza.profissionais.dtos.ProfissionalResponse;
import com.salaobeleza.profissionais.entities.Profissional;
import com.salaobeleza.profissionais.mapper.ProfissionalMapper;
import com.salaobeleza.profissionais.repositories.ProfissionalRepository;
import com.salaobeleza.profissionais.services.exceptions.ResourceNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository repository;

    @Autowired
    private ProfissionalMapper mapper;

    public String novoProfissional(ProfissionalRequest request) {
        var profissional = repository.save(mapper.toProfissional(request));
        return profissional.getId();
    }

    public void atualizaProfissional(ProfissionalRequest request) {
        var profissional = repository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Profissional com id '%s' não encontrado", request.getId())));
        mergeProfissional(profissional, request);
        repository.save(profissional);
    }

    private void mergeProfissional(Profissional profissional, ProfissionalRequest request) {
        if(StringUtils.isNotBlank(request.getNome())) {
            profissional.setNome(request.getNome());
        }
        if(StringUtils.isNotBlank(request.getCpf())) {
            profissional.setCpf(request.getCpf());
        }
        if(StringUtils.isNotBlank(request.getEmail())) {
            profissional.setEmail(request.getEmail());
        }
        if(StringUtils.isNotBlank(request.getTelefone())) {
            profissional.setTelefone(request.getTelefone());
        }
    }

    @Transactional(readOnly = true)
    public ProfissionalResponse buscaPorId(String id) {
        return repository.findById(id)
                .map(mapper::toProfissionalResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Profissional com id '%s' não encontrado", id)));
    }

    @Transactional(readOnly = true)
    public List<ProfissionalResponse> buscaTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toProfissionalResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletaProfissional(String id) {
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

