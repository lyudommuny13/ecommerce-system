package ctm.mptc.kh.ecommerce.business.persistence.adapter;

import ctm.mptc.kh.ecommerce.business.domain.entity.OrderApproval;
import ctm.mptc.kh.ecommerce.business.domain.port.output.OrderApprovalRepository;
import ctm.mptc.kh.ecommerce.business.persistence.entity.OrderApprovalEntity;
import ctm.mptc.kh.ecommerce.business.persistence.mapper.OrderApprovalPersistenceMapper;
import ctm.mptc.kh.ecommerce.business.persistence.repository.OrderApprovalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderApprovalRepositoryAdapter implements OrderApprovalRepository {
    private final OrderApprovalJpaRepository orderApprovalJpaRepository;
    private final OrderApprovalPersistenceMapper orderApprovalPersistenceMapper;

    @Override
    public OrderApproval save(OrderApproval orderApproval) {
        OrderApprovalEntity orderApprovalEntity = orderApprovalPersistenceMapper.orderApprovalToOrderApprovalEntity(orderApproval);

        OrderApprovalEntity savedOrderApprovalEntity = orderApprovalJpaRepository.save(orderApprovalEntity);

        return orderApprovalPersistenceMapper.orderApprovalEntityToOrderApproval(savedOrderApprovalEntity);
    }
}
