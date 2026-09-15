package ctm.mptc.kh.ecommerce.order.domain.event;

import ctm.mptc.kh.ecommerce.order.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent{
    public OrderPaidEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
