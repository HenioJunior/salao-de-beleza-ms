package com.salaobeleza.clientes.dtos;

import com.salaobeleza.clientes.entites.Cliente;

public class ClienteResponse {

    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public ClienteResponse(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Cliente toModel() {
        return new Cliente(nome, cpf, email, telefone);
    }
}
