package ctm.mptc.kh.ecommerce.order.domain.dto;

import java.util.UUID;

public record CreateOrderResult(
        UUID orderId
) {
}

// SELECT -> Query -> TRANSACTION READ ONLY