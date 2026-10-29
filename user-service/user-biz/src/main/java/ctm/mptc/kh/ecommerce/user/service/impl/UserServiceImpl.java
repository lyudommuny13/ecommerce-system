package ctm.mptc.kh.ecommerce.user.service.impl;

import ctm.mptc.kh.ecommerce.restapi.exception.PasswordMismatchException;
import ctm.mptc.kh.ecommerce.user.dto.UserCreateRequest;
import ctm.mptc.kh.ecommerce.user.dto.UserResponse;
import ctm.mptc.kh.ecommerce.user.dto.UserUpdateRequest;
import ctm.mptc.kh.ecommerce.user.entity.UserEntity;
import ctm.mptc.kh.ecommerce.user.exception.UserNotFoundException;
import ctm.mptc.kh.ecommerce.user.exception.UsernameConflictException;
import ctm.mptc.kh.ecommerce.user.mapper.UserMapper;
import ctm.mptc.kh.ecommerce.user.repository.UserRepository;
import ctm.mptc.kh.ecommerce.user.service.KeycloakRoleService;
import ctm.mptc.kh.ecommerce.user.service.KeycloakUserService;
import ctm.mptc.kh.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final KeycloakUserService keycloakUserService;
    private final KeycloakRoleService keycloakRoleService;

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        UserEntity userEntity = userMapper.toEntity(userCreateRequest);

        // Validate username
        if (userRepository.existsByUsername(userCreateRequest.username())) {
            throw new UsernameConflictException(HttpStatus.CONFLICT,
                    "Username already exists");
        }

        // Validate password
        if (!userCreateRequest.password().equals(userCreateRequest.confirmedPassword())) {
            throw new PasswordMismatchException(HttpStatus.BAD_REQUEST,
                    "Password does not match");
        }

        // Save user into Keycloak
        // After saved user, save user into DB
        UserRepresentation userRepresentation = userMapper.toRepresentation(userCreateRequest);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userCreateRequest.password());

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        keycloakUserService.createKeycloakUser(userRepresentation);
        UserRepresentation createdUser = keycloakUserService.getKeycloakUserByUsername(
                userEntity.getUsername()
        );
        log.info("User created: {}", createdUser);
        keycloakRoleService.assignRoles(createdUser.getId(), userCreateRequest.roleName());

        // Saving user into database
        userEntity.setId(createdUser.getId());
        userRepository.save(userEntity);



        return userMapper.toResponse(userEntity, createdUser);
    }

    @Override
    public UserResponse update(String id,UserUpdateRequest userUpdateRequest) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found"));

        userMapper.toEntity(userUpdateRequest, userEntity);
        userRepository.save(userEntity);

        UserRepresentation userRepresentation = keycloakUserService
                .getKeycloakUserByUsername(userEntity.getUsername());

        // Attempt to Keycloak user
        if (!(userUpdateRequest.familyName() == null && userUpdateRequest.givenName() == null)) {
            if (userUpdateRequest.familyName() != null) {
                userRepresentation.setFirstName(userUpdateRequest.familyName());
            }

            if (userUpdateRequest.givenName() != null) {
                userRepresentation.setLastName(userUpdateRequest.givenName());
            }

            keycloakUserService.updateKeycloakUser(id, userRepresentation);
        }

        return userMapper.toResponse(userEntity, userRepresentation);
    }

    @Override
    public UserResponse getUserById(String id) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found"));

        UserRepresentation userRepresentation = keycloakUserService.getKeycloakUserById(id);

        return userMapper.toResponse(
                userEntity,
                userRepresentation
        );
    }

    @Override
    public List<UserResponse> getUsersByRoleName(String roleName) {
        return keycloakRoleService.getUsersByRoleName(roleName)
                .stream()
                .map(userRepresentation -> userRepository
                        .findById(userRepresentation.getId())
                        .map(userEntity -> userMapper.toResponse(userEntity, userRepresentation))
                        .orElseGet(() -> UserResponse.builder()
                                .id(userRepresentation.getId())
                                .familyName(userRepresentation.getFirstName())
                                .givenName(userRepresentation.getLastName())
                                .build()))
                .toList();
    }

    @Override
    public List<UserResponse> getUsers() {
        return keycloakUserService.getKeycloakUsers()
                .stream()
                .map(userRepresentation -> UserResponse.builder()
                        .id(userRepresentation.getId())
                        .familyName(userRepresentation.getFirstName())
                        .givenName(userRepresentation.getLastName())
                        .build())
                .toList();
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        UserRepresentation userRepresentation = keycloakUserService.getKeycloakUserByUsername(username);

        return userRepository
                .findById(userRepresentation.getId())
                .map(userEntity -> userMapper.toResponse(userEntity, userRepresentation))
                .orElseGet(() -> UserResponse.builder()
                        .id(userRepresentation.getId())
                        .familyName(userRepresentation.getFirstName())
                        .givenName(userRepresentation.getLastName())
                        .build());
    }

    @Override
    public void delete(String id) {
        keycloakUserService.deleteKeycloakUser(id);

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

}