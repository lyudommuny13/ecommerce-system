package ctm.mptc.kh.ecommerce.user.service;

import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface KeycloakUserService {

    void createKeycloakUser(UserRepresentation userRepresentation);

    UserRepresentation getKeycloakUserByUsername(String username);

    UserRepresentation getKeycloakUserById(String id);

    List<UserRepresentation> getKeycloakUsers();

    void updateKeycloakUser(String id, UserRepresentation userRepresentation);

    void deleteKeycloakUser(String id);
}
