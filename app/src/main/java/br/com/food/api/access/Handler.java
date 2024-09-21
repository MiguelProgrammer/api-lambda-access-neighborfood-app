package br.com.food.api.access;

import br.com.food.api.access.adapter.in.ClienteRequest;
import br.com.food.api.access.adapter.out.ClienteResponse;
import br.com.food.api.access.adapter.out.ResponseDTO;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(
            APIGatewayProxyRequestEvent request, Context context) {

        ObjectMapper mapper = new ObjectMapper();
        var logger = context.getLogger();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ClienteRequest clienteRequest;
        String url = "http://18.230.226.20/neighborfood/login";
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            clienteRequest = mapper.readValue(request.getBody(), ClienteRequest.class);
            String resp = restTemplate.postForObject(url, clienteRequest, String.class);
            ClienteResponse clienteResponse = mapper.readValue(resp, ClienteResponse.class);
            responseDTO.setClient(clienteRequest);

            if (clienteResponse.getId() != null) {
                ClienteRequest client = responseDTO.getClient();
                responseDTO.getClient().setEmail(client.getEmail() == null ? "empty-mail@provider.com" : client.getEmail());
                responseDTO.setIsAuthenticated(Boolean.TRUE);
            }

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody(responseDTO.toString())
                    .withIsBase64Encoded(false);
        } catch (Exception e) {
            logger.log(e.getMessage());
            logger.log("Error lambda access -> " + e);
        } finally {
            logger.log("REQUEST -> {}" + request);
        }
        return null;
    }

}
