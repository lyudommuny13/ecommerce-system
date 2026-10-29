package ctm.mptc.kh.ecommerce.user.service.impl;

import ctm.mptc.kh.ecommerce.user.config.KeycloakAdminClientConfig;
import ctm.mptc.kh.ecommerce.user.exception.KeycloakException;
import ctm.mptc.kh.ecommerce.user.service.KeycloakRoleService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakRoleServiceImpl implements KeycloakRoleService {

    private final Keycloak keycloak;

    @Override
    public void assignRoles(String userId, String roleNameWithDelimiter) {

        String[] roleNameList = roleNameWithDelimiter.split(",");
        List<RoleRepresentation> roleRepresentationList = Arrays.stream(roleNameList)
                .map(this::getKeycloakRoleByName)
                .toList();

        keycloak.realm(KeycloakAdminClientConfig.REALM_NAME)
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(roleRepresentationList);
    }

    @Override
    public List<UserRepresentation> getUsersByRoleName(String roleName) {
        try {
            return new ArrayList<>(keycloak.realm(KeycloakAdminClientConfig.REALM_NAME)
                    .roles()
                    .get(roleName)
                    .getUserMembers());
        } catch (NotFoundException e) {
            throw new KeycloakException(HttpStatus.NOT_FOUND,
                    "Role '" + roleName + "' not found in Keycloak");
        }
    }

    private RoleRepresentation getKeycloakRoleByName(String roleName) {
        return keycloak.realm(KeycloakAdminClientConfig.REALM_NAME)
                .roles()
                .get(roleName)
                .toRepresentation();
    }

}