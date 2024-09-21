package br.com.food.api.access.adapter.out;


import br.com.food.api.access.adapter.in.ClienteRequest;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private ClienteRequest client;
    private Boolean isAuthenticated = Boolean.FALSE;

    @Override
    public String toString() {
        return "Client:" +
                "   \nNome: " + client.getNome() +
                "   \nEmail: " + client.getEmail() +
                "   \nCpf: " + client.getCpf() +
                "\nAuthenticated = " + isAuthenticated;
    }
}

