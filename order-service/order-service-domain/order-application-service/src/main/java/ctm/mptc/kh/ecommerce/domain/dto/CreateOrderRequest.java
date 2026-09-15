package ctm.mptc.kh.ecommerce.domain.dto;

import ctm.mptc.kh.ecommerce.domain.valueobject.BusinessId;
import ctm.mptc.kh.ecommerce.domain.valueobject.CustomerId;
import ctm.mptc.kh.ecommerce.domain.valueobject.Money;
import ctm.mptc.kh.ecommerce.domain.valueobject.StreetAddress;

public record CreateOrderRequest(
        CustomerId customerId,
        BusinessId businessId,
        StreetAddress streetAddress,
        Money price
) {
}
