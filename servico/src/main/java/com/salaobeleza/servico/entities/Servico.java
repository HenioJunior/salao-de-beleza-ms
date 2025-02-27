package com.salaobeleza.servico.entities;

import com.salaobeleza.servico.enums.TipoServico;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "servicos")
public class Servico {

    @Id
    private String id;
    private double valor;
    private TipoServico tipo;

}
