package ctm.mptc.kh.ecommerce.order.domain.port.output;

import ctm.mptc.kh.ecommerce.order.domain.entity.Order;

public interface OrderRepository {

    Order saveOrder(Order order);
}
