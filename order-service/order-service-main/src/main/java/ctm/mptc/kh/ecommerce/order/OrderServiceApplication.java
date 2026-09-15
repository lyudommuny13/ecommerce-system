package ctm.mptc.kh.ecommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
        "ctm.mptc.kh.ecommerce.order.persistence"
})
@EnableJpaRepositories(basePackages = {
        "ctm.mptc.kh.ecommerce.order.persistence"
})
@SpringBootApplication
public class OrderServiceApplication {
    static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
