package ctm.mptc.kh.ecommerce.business;

import ctm.mptc.kh.ecommerce.business.domain.service.BusinessDomainService;
import ctm.mptc.kh.ecommerce.business.domain.service.BusinessDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public BusinessDomainService businessDomainService() {
        return new BusinessDomainServiceImpl();
    }
}
