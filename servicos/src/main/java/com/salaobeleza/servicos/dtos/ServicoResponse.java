package com.salaobeleza.servicos.dtos;

import com.salaobeleza.servicos.entities.Servico;
import com.salaobeleza.servicos.enums.TipoServico;

public class ServicoResponse {

    private String id;
    private double valor;
    private TipoServico tipo;

    public ServicoResponse(Servico servico) {
        this.id = servico.getId();
        this.valor = servico.getValor();
        this.tipo = servico.getTipo();
    }

    public String getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public TipoServico getTipo() {
        return tipo;
    }
}
