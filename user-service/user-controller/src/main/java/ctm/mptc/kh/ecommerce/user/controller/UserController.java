package ctm.mptc.kh.ecommerce.user.controller;

import ctm.mptc.kh.ecommerce.user.dto.UserCreateRequest;
import ctm.mptc.kh.ecommerce.user.dto.UserResponse;
import ctm.mptc.kh.ecommerce.user.dto.UserUpdateRequest;
import ctm.mptc.kh.ecommerce.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse create(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest);
    }

    @GetMapping("/role/{roleName}")
    public List<UserResponse> getUsersByRoleName(@PathVariable String roleName) {
        return userService.getUsersByRoleName(roleName);
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PatchMapping("/{id}")
    public UserResponse update(@PathVariable String id,
                               @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(id, userUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}
