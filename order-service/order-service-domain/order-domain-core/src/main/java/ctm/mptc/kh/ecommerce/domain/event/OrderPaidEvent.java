package ctm.mptc.kh.ecommerce.domain.event;

import ctm.mptc.kh.ecommerce.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent{
    public OrderPaidEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
