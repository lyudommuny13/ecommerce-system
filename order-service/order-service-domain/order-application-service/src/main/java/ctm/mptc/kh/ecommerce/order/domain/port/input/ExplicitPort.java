package ctm.mptc.kh.ecommerce.order.domain.port.input;

import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderCommand;

public interface ExplicitPort {
    void execute(CreateOrderCommand createOrderCommand);
}
