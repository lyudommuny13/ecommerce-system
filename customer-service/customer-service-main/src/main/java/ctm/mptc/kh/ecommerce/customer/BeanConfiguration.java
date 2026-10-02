package ctm.mptc.kh.ecommerce.customer;


import ctm.mptc.kh.ecommerce.customer.domain.service.CustomerDomainService;
import ctm.mptc.kh.ecommerce.customer.domain.service.CustomerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public CustomerDomainService customerDomainService() {
        return new CustomerDomainServiceImpl();
    }
}
