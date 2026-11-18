package ctm.mptc.kh.ecommerce.customer.restapi.controller;

import ctm.mptc.kh.ecommerce.core.PageResponse;
import ctm.mptc.kh.ecommerce.customer.client.dto.UserResponse;
import ctm.mptc.kh.ecommerce.customer.client.feign.UserFeignClient;
import ctm.mptc.kh.ecommerce.customer.domain.dto.CustomerResult;
import ctm.mptc.kh.ecommerce.customer.domain.dto.GetCustomersQuery;
import ctm.mptc.kh.ecommerce.customer.domain.usecase.GetAllCustomersUseCase;
import ctm.mptc.kh.ecommerce.customer.domain.usecase.GetCustomerByIdUseCase;
import ctm.mptc.kh.ecommerce.customer.restapi.dto.CustomerResponse;
import ctm.mptc.kh.ecommerce.customer.restapi.mapper.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerQueryController {

    private final UserFeignClient userFeignClient;
    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final CustomerWebMapper customerWebMapper;

    @GetMapping
    public PageResponse<CustomerResponse> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        PageResponse<CustomerResult> result = getAllCustomersUseCase.execute(new GetCustomersQuery(page, size));
        return customerWebMapper.toCustomerResponse(result);
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getById(@PathVariable UUID customerId) {
        return customerWebMapper.toCustomerResponse(getCustomerByIdUseCase.execute(customerId));
    }

    @GetMapping("/check-user/{userId}")
    public UserResponse checkUser(@PathVariable String userId) {
        return userFeignClient.getById(userId);
    }

    @GetMapping("/by-user/{userId}")
    public UserResponse getCustomerByUserId(@PathVariable String userId) {
        return userFeignClient.getById(userId);
    }
}
