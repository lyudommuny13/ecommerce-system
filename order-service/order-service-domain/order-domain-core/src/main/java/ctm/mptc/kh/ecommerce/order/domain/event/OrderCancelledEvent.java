package ctm.mptc.kh.ecommerce.order.domain.event;

import ctm.mptc.kh.ecommerce.order.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent{
    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
