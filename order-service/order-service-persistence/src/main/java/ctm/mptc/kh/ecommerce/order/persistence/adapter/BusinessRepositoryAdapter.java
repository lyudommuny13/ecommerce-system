package ctm.mptc.kh.ecommerce.order.persistence.adapter;

import ctm.mptc.kh.ecommerce.order.domain.entity.Business;
import ctm.mptc.kh.ecommerce.order.domain.port.output.BusinessRepository;
import ctm.mptc.kh.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import ctm.mptc.kh.ecommerce.order.persistence.repository.BusinessJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BusinessRepositoryAdapter implements BusinessRepository{

    private final BusinessJpaRepository businessJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    @Override
    public Optional<Business> findBusiness(UUID businessId) {
        return businessJpaRepository.findById(businessId)
                .map(orderPersistenceMapper::businessEntityToBusiness);
    }

}
