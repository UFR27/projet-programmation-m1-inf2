package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.UserDAO;
import fr.pantheonsorbonne.dto.UserDTO;
import fr.pantheonsorbonne.entity.User;
import fr.pantheonsorbonne.exception.InvalidUserException;
import fr.pantheonsorbonne.exception.UserAlreadyExistWithTheSameEmail;
import fr.pantheonsorbonne.gateway.InvoiceGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Inject
    UserDAO userDAO;

    @Inject
    InvoiceGateway invoiceGateway;

    public UserDTO getUserByID(Long id) {
        User user = userDAO.getById(id);
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getName(), user.getEmail());
    }

    @Transactional
    public Long checkAndSaveUSer(UserDTO userDTO) throws InvalidUserException, UserAlreadyExistWithTheSameEmail {

        // vérification métier (email est ok)

        if (!userDTO.email().contains("@")) {
            throw new InvalidUserException("email is malformed");
        }

        // vérification existance
        if (userDAO.isUserPresent(userDTO.email())) {
            throw new UserAlreadyExistWithTheSameEmail();
        }

        User user = new User();
        user.setEmail(userDTO.email());
        user.setName(userDTO.name());

        userDAO.saveUser(user);

        invoiceGateway.handleNewUser(userDTO);
        return user.getId();


    }
}
