package br.com.food.api.access;

import br.com.food.api.access.adapter.in.ClienteRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.springframework.web.client.RestTemplate;

public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(
            APIGatewayProxyRequestEvent request, Context context) {

        var logger = context.getLogger();

        logger.log("Request received - " + request.getBody());
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:80/neighborfood/login";

        ClienteRequest clientResponse = restTemplate.getForEntity(fooResourceUrl, ClienteRequest.class).getBody();

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody(clientResponse != null ? clientResponse.toString() : new ClienteRequest().toString())
                .withIsBase64Encoded(false);
    }

}
