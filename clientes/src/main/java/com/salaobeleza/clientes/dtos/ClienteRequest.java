package com.salaobeleza.clientes.dtos;

import com.salaobeleza.clientes.entites.Cliente;

public class ClienteRequest {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public ClienteRequest(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
    }

    public ClienteRequest() {
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
}
