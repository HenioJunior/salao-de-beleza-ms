package com.salaobeleza.clientes.entites;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
