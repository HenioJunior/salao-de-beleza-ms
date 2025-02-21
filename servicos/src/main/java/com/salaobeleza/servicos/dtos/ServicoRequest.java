package com.salaobeleza.servicos.dtos;

import com.salaobeleza.servicos.enums.TipoServico;

public class ServicoRequest {

    private double valor;
    private TipoServico tipo;

    public ServicoRequest(double valor, TipoServico tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public ServicoRequest() {}

    public ServicoRequest(ServicoRequest servico) {
        this.valor = servico.valor;
        this.tipo = servico.tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoServico getTipo() {
        return tipo;
    }

    public void setTipo(TipoServico tipo) {
        this.tipo = tipo;
    }
}
