package ctm.mptc.kh.ecommerce.order.persistence.adapter;

import ctm.mptc.kh.ecommerce.order.domain.entity.Order;
import ctm.mptc.kh.ecommerce.order.domain.port.output.OrderRepository;
import ctm.mptc.kh.ecommerce.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private  final OrderJpaRepository orderJpaRepository;

    @Override
    public Order saveOrder(Order order) {
        return null;
    }
}
