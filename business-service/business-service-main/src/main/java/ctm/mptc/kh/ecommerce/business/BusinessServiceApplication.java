package ctm.mptc.kh.ecommerce.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
        "ctm.mptc.kh.ecommerce.business.persistence"
})

@EnableJpaRepositories(basePackages = {
        "ctm.mptc.kh.ecommerce.business.persistence"
})

@SpringBootApplication
public class BusinessServiceApplication {
    static void main(String[] args) {
        SpringApplication.run(BusinessServiceApplication.class, args);
    }
}
