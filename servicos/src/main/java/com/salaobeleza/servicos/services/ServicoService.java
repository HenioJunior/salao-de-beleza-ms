package com.salaobeleza.servicos.services;

import com.salaobeleza.servicos.dtos.ServicoRequest;
import com.salaobeleza.servicos.dtos.ServicoResponse;
import com.salaobeleza.servicos.entities.Servico;
import com.salaobeleza.servicos.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    @Transactional(readOnly = true)
    public ServicoResponse buscaPorId(String id) {
        Servico servico = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso não encontrado"));
        return new ServicoResponse(servico);
    }

    @Transactional(readOnly = true)
    public Page<ServicoResponse> buscaTodos(Pageable pageable) {
        Page<Servico> result = repository.findAll(pageable);
        return result.map(ServicoResponse::new);
    }

    public ServicoResponse novoServico(ServicoRequest request) {
        Servico servico = new Servico(request.getValor(), request.getTipo());
        repository.save(servico);
        return new ServicoResponse(servico);
    }

    public ServicoResponse atualizaServico(String id, ServicoRequest request) {
        Servico servico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servico não localizado"));
        servico.setValor(request.getValor());
        servico.setTipo(request.getTipo());
        repository.save(servico);
        return new ServicoResponse(servico);
    }

    public void deletaServico(String id) {
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

