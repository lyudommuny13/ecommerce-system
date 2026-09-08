package ctm.mptc.kh.ecommerce.domain.event;

import ctm.mptc.kh.ecommerce.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent{
    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
