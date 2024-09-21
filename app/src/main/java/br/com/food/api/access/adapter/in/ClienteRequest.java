package br.com.food.api.access.adapter.in;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteRequest {

    private String nome;
    private String email;
    private String cpf;

}

