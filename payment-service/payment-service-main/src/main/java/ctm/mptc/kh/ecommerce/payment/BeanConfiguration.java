package ctm.mptc.kh.ecommerce.payment;

import ctm.mptc.kh.ecommerce.payment.domain.service.PaymentDomainService;
import ctm.mptc.kh.ecommerce.payment.domain.service.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public PaymentDomainService paymentDomainService() {
        return new PaymentDomainServiceImpl();
    }
}
