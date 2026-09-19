package ctm.mptc.kh.ecommerce.order.persistence.repository;

import ctm.mptc.kh.ecommerce.order.persistence.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessJpaRepository extends JpaRepository<BusinessEntity, UUID> {
}
