package ctm.mptc.kh.ecommerce.user.service;

import ctm.mptc.kh.ecommerce.user.dto.UserCreateRequest;
import ctm.mptc.kh.ecommerce.user.dto.UserResponse;
import ctm.mptc.kh.ecommerce.user.dto.UserUpdateRequest;
import java.util.List;

public interface UserService {

    UserResponse create(UserCreateRequest userCreateRequest);

    UserResponse update(String id, UserUpdateRequest userUpdateRequest);

    UserResponse getUserById(String id);

    List<UserResponse> getUsersByRoleName(String roleName);

    List<UserResponse> getUsers();

    UserResponse getUserByUsername(String username);

    void delete(String id);
}
