package com.salaobeleza.clientes.mapper;

import com.salaobeleza.clientes.dtos.ClienteRequest;
import com.salaobeleza.clientes.dtos.ClienteResponse;
import com.salaobeleza.clientes.entites.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toCliente(ClienteRequest request) {
        return Cliente.builder()
                .nome(request.getNome())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .build();
    }

    public ClienteResponse toClienteResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }
}
