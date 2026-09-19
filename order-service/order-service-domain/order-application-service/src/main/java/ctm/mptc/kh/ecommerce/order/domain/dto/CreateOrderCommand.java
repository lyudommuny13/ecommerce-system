package ctm.mptc.kh.ecommerce.order.domain.dto;

import ctm.mptc.kh.ecommerce.domain.valueobject.BusinessId;
import ctm.mptc.kh.ecommerce.domain.valueobject.CustomerId;
import ctm.mptc.kh.ecommerce.domain.valueobject.Money;
import ctm.mptc.kh.ecommerce.domain.valueobject.StreetAddress;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateOrderCommand(
        UUID customerId,
        UUID businessId,
        BigDecimal price,
        CommandOrderAddress deliveryAddress,
        List<CommandOrderItem> items
) {
}

// INSERT, UPDATE, DELETE -> Command -> TRANSACTION
// No validation