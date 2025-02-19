package com.salaobeleza.clientes.services;


import com.salaobeleza.clientes.dtos.ClienteRequest;
import com.salaobeleza.clientes.dtos.ClienteResponse;
import com.salaobeleza.clientes.entites.Cliente;
import com.salaobeleza.clientes.repositories.ClienteRepository;
import com.salaobeleza.clientes.services.exceptions.DatabaseException;
import com.salaobeleza.clientes.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public ClienteResponse buscaPorId(String id) {
        Cliente cliente = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClienteResponse(cliente);
    }

    @Transactional(readOnly = true)
    public Page<ClienteResponse> buscaTodos(Pageable pageable) {
        Page<Cliente> result = repository.findAll(pageable);
        return result.map(ClienteResponse::new);
    }

    public ClienteResponse novoCliente(ClienteRequest request) {
        Cliente cliente = new Cliente(request.getNome(), request.getCpf(),
                request.getEmail(), request.getTelefone());
        repository.save(cliente);
        return new ClienteResponse(cliente);
    }

    public ClienteResponse AtualizaCliente(String id, ClienteRequest request) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não localizado"));
        cliente.setNome(request.getNome());
        cliente.setCpf(request.getCpf());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        repository.save(cliente);
        return new ClienteResponse(cliente);
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

