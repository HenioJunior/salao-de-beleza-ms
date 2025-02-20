package com.salaobeleza.agendamentos.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteResponse {

    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "nome")
    private String nome;
    @JsonProperty(value = "cpf")
    private String cpf;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "telefone")
    private String telefone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
