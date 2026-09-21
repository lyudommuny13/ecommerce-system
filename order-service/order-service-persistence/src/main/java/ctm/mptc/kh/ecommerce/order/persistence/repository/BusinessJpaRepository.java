package ctm.mptc.kh.ecommerce.order.persistence.repository;

import ctm.mptc.kh.ecommerce.order.persistence.entity.BusinessEntity;
import ctm.mptc.kh.ecommerce.order.persistence.entity.BusinessIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BusinessJpaRepository extends JpaRepository<BusinessEntity, BusinessIdEntity> {
    List<BusinessEntity> findByBusinessIdAndProductIdIn(
        UUID businessId,
        List<UUID> productIds
    );
}
