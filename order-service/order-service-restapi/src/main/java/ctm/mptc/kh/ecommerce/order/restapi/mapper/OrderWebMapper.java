package ctm.mptc.kh.ecommerce.order.restapi.mapper;

import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderCommand;
import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderResult;
import ctm.mptc.kh.ecommerce.order.restapi.dto.OrderCreateRequest;
import ctm.mptc.kh.ecommerce.order.restapi.dto.OrderCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {
    // source = OrderCreateRequest
    // target = CreateOrderCommand

    @Mapping(source = "orderAddress", target = "deliveryAddress")
    CreateOrderCommand orderCreateRequestToCreateOrderCommand(
            OrderCreateRequest orderCreateRequest
    );

    OrderCreateResponse createOrderResultToOrderCreateResponse(
            CreateOrderResult createOrderResult
    );
}
