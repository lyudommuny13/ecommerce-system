package ctm.mptc.kh.ecommerce.user.mapper;

import ctm.mptc.kh.ecommerce.user.dto.UserCreateRequest;
import ctm.mptc.kh.ecommerce.user.dto.UserResponse;
import ctm.mptc.kh.ecommerce.user.dto.UserUpdateRequest;
import ctm.mptc.kh.ecommerce.user.entity.UserEntity;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default UserResponse toResponse(UserEntity userEntity, UserRepresentation userRepresentation) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .familyName(userRepresentation.getFirstName())
                .givenName(userRepresentation.getLastName())
                .gender(userEntity.getGender())
                .address(userEntity.getAddress())
                .birthday(userEntity.getBirthday())
                .mobilePhone(userEntity.getMobilePhone())
                .build();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntity(UserUpdateRequest request, @MappingTarget UserEntity entity);

    UserEntity toEntity(UserCreateRequest userCreateRequest);

    UserResponse toResponse(UserEntity userEntity);

    @Mapping(source = "familyName", target = "firstName")
    @Mapping(source = "givenName", target = "lastName")
    UserRepresentation toRepresentation(UserCreateRequest userCreateRequest);
}
