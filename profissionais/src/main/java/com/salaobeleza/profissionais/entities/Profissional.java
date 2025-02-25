package com.salaobeleza.profissionais.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "profissionais")
public class Profissional {

    @Id
    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
