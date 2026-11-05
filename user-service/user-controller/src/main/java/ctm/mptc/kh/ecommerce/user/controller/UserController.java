package ctm.mptc.kh.ecommerce.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import ctm.mptc.kh.ecommerce.restapi.dto.PageResponse;
import ctm.mptc.kh.ecommerce.user.dto.UserCreateRequest;
import ctm.mptc.kh.ecommerce.user.dto.UserResponse;
import ctm.mptc.kh.ecommerce.user.dto.UserUpdateRequest;
import ctm.mptc.kh.ecommerce.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PatchMapping("/{id}")
    public UserResponse update(@PathVariable String id,
                               @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(id, userUpdateRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable String id) {
        return userService.getById(id);
    }

    @GetMapping("/username/{username}")
    public UserResponse getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @SentinelResource(
            value = "getUsersByRoleName",
            fallback = "getUsersByRoleNameFallback"
//            blockHandler = "getUsersByRoleNameBlocked"
    )
    @GetMapping("/roles/{roleName}")
    public PageResponse<UserResponse> getUsersByRoleName(@PathVariable String roleName,
                                                         @RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                         @RequestParam(defaultValue = "15", required = false) int pageSize) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return userService.getUsersByRoleName(roleName, PageRequest.of(pageNumber, pageSize));
    }

//    public String getUsersByRoleNameFallback(Throwable t) {
//        return "getUsersByRoleName fallback";
//    }

//    public PageResponse<UserResponse> getUsersByRoleNameBlocked(String roleName, int pageNumber, int pageSize, BlockException e) {
//        return PageResponse.<UserResponse>builder()
//                .items(List.of())
//                .build();
//    }

    @GetMapping
    public PageResponse<UserResponse> getUsers(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                               @RequestParam(defaultValue = "15", required = false) int pageSize) {
        return userService.getUsers(PageRequest.of(pageNumber, pageSize));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        userService.deleteById(id);
    }

}
