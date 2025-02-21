package com.salaobeleza.agendamentos.entities;

import org.springframework.data.annotation.Id;

public class AgendamentoServico {

        
    @Id
    private String id;
    private String tipoServico;

    public AgendamentoServico(String id, String tipoServico) {
        this.id = id;
        this.tipoServico = tipoServico;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }
}
