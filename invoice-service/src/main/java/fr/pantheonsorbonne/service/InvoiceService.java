package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.UserDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.UUID;

@ApplicationScoped
@Named("invoice")
@RegisterForReflection
public class InvoiceService {

    public UserDTO createInvoiceForUser(UserDTO userDTO) {
        return new UserDTO(userDTO.name(), userDTO.email(), UUID.randomUUID().toString());

    }
}
