package br.com.food.api.access.adapter.out;


import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private List<?> pedidos;
    private String notificacao;

}

