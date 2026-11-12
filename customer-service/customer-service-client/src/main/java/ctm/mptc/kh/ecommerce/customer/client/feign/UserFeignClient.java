package ctm.mptc.kh.ecommerce.customer.client.feign;

import ctm.mptc.kh.ecommerce.customer.client.dto.UserResponse;
import ctm.mptc.kh.ecommerce.customer.client.fallback.UserFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service", // name: service-name in nacos
        path = "/api/users", // path: api main path
        fallback = UserFeignClientFallback.class
)
public interface UserFeignClient {

    @GetMapping("/{id}")
    UserResponse getById(@PathVariable String id);
}
