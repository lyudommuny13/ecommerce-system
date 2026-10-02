package ctm.mptc.kh.ecommerce.customer.domain.event;

import ctm.mptc.kh.ecommerce.customer.domain.entity.Customer;

import java.time.ZonedDateTime;

public class CustomerCreatedEvent extends CustomerEvent{
    private final ZonedDateTime createdAt;

    public CustomerCreatedEvent(Customer customer, ZonedDateTime createdAt){
        super(customer);
        this.createdAt = createdAt;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
