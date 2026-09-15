package ctm.mptc.kh.ecommerce.order.domain.dto;

import ctm.mptc.kh.ecommerce.domain.valueobject.OrderId;

public record CreateOrderResponse(
        OrderId orderId
) {
}
