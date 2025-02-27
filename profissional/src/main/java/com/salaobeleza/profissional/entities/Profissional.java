package com.salaobeleza.profissional.entities;

import com.salaobeleza.profissional.dto.ServicoResponse;
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
    private ServicoResponse servico;
}
