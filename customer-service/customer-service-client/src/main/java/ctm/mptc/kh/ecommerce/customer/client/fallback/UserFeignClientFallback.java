package ctm.mptc.kh.ecommerce.customer.client.fallback;

import ctm.mptc.kh.ecommerce.customer.client.dto.UserResponse;
import ctm.mptc.kh.ecommerce.customer.client.exception.UserServiceUnavailableException;
import ctm.mptc.kh.ecommerce.customer.client.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public UserResponse getById(String id) {
        log.info("UserFeignClientFallback getById " + id);

        throw new UserServiceUnavailableException("User service unavailable now, please try again later");
    }
}
