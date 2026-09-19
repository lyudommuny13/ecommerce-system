package ctm.mptc.kh.ecommerce.order.restapi.mapper;

import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderCommand;
import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderResult;
import ctm.mptc.kh.ecommerce.order.restapi.dto.OrderCreateRequest;
import ctm.mptc.kh.ecommerce.order.restapi.dto.OrderCreateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {
    // source = OrderCreateRequest
    // target = CreateOrderCommand

    CreateOrderCommand orderCreateRequestToCreateOrderCommand(
            OrderCreateRequest orderCreateRequest
    );

    OrderCreateResponse createOrderResultToOrderCreateResponse(
            CreateOrderResult createOrderResult
    );
}
