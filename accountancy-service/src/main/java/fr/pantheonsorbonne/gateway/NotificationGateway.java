package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.dto.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class NotificationGateway implements Processor {


    @RestClient
    NotificationResource notificationResource;

    @Override
    public void process(Exchange exchange) throws Exception {
        var userDTO = exchange.getMessage().getBody(UserDTO.class);

        try (Response resp = notificationResource.pushNotif("reçu " + userDTO.toString())) {
            if (resp.getStatus() != 200) {
                throw new Exception("Error Code" + resp.getStatus());
            }
        }
    }
}
