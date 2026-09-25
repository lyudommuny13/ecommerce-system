package ctm.mptc.kh.ecommerce.order;

/*
    How to configure beans
    1. Annotation-based configuration
    2. Java-based (method)
*/

import ctm.mptc.kh.ecommerce.order.domain.service.OrderDomainService;
import ctm.mptc.kh.ecommerce.order.domain.service.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
