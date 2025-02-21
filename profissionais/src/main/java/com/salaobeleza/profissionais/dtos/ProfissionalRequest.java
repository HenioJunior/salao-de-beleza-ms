package com.salaobeleza.profissionais.dtos;


import jakarta.validation.constraints.NotBlank;

public class ProfissionalRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    private String email;
    @NotBlank
    private String telefone;

    public ProfissionalRequest(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public ProfissionalRequest() {}

    public ProfissionalRequest(ProfissionalRequest profissional) {
        this.nome = profissional.getNome();
        this.cpf = profissional.getCpf();
        this.email = profissional.getEmail();
        this.telefone = profissional.getTelefone();
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
