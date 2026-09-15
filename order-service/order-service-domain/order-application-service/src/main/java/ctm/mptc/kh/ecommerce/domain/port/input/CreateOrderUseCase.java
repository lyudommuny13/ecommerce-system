package ctm.mptc.kh.ecommerce.domain.port.input;

import ctm.mptc.kh.ecommerce.domain.dto.CreateOrderRequest;

public interface CreateOrderUseCase {
    void execute(CreateOrderRequest createOrderRequest);
}
