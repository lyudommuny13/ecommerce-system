package ctm.mptc.kh.ecommerce.business.persistence.repository;

import ctm.mptc.kh.ecommerce.business.persistence.entity.OrderApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderApprovalJpaRepository extends JpaRepository<OrderApprovalEntity, UUID> {

}
