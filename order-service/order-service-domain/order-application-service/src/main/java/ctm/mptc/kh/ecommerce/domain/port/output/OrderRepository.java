package ctm.mptc.kh.ecommerce.domain.port.output;

import ctm.mptc.kh.ecommerce.domain.entity.Order;

public interface OrderRepository {

    Order saveOrder(Order order);
}
