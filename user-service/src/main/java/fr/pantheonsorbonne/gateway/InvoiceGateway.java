package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.dto.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class InvoiceGateway {

    @Inject
    ProducerTemplate producerTemplate;

    public void handleNewUser(UserDTO user) {
        producerTemplate.sendBody("direct:newUser", user);

    }
}
