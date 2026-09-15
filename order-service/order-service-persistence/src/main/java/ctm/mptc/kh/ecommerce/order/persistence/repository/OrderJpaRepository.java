package ctm.mptc.kh.ecommerce.order.persistence.repository;

import ctm.mptc.kh.ecommerce.order.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// Benefit of using SPring Data JPA => Relational Database
// 1. Boilerplate code
// 2. Abstraction Repository
// 3. Derived Query Method (Auto generate SQL)
// 4. Object Relational Mapping (ORM) Hibernates
// 5. Specification (dynamic Query)

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
}
