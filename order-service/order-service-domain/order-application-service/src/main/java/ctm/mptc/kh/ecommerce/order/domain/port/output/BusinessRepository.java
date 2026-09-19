package ctm.mptc.kh.ecommerce.order.domain.port.output;

import ctm.mptc.kh.ecommerce.order.domain.entity.Business;

import java.util.Optional;
import java.util.UUID;

public interface BusinessRepository {
    Optional<Business> findBusiness(UUID businessId);
}
