package ctm.mptc.kh.ecommerce.order.domain.port.input;

import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderRequest;

public interface CreateOrderUseCase {
    void execute(CreateOrderRequest createOrderRequest);
}
