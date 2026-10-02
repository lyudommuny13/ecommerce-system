package ctm.mptc.kh.ecommerce.customer.domain.event;

import ctm.mptc.kh.ecommerce.customer.domain.entity.Customer;

import java.time.ZonedDateTime;

public class CustomerUpdatedEvent extends CustomerEvent{
    private final ZonedDateTime updatedAt;

    public CustomerUpdatedEvent(Customer customer, ZonedDateTime updatedAt){
        super(customer);
        this.updatedAt = updatedAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
}
