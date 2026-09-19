package ctm.mptc.kh.ecommerce.order.restapi.controller;

import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderCommand;
import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderResult;
import ctm.mptc.kh.ecommerce.order.domain.usecase.CreateOrderUseCase;
import ctm.mptc.kh.ecommerce.order.restapi.dto.OrderCreateRequest;
import ctm.mptc.kh.ecommerce.order.restapi.dto.OrderCreateResponse;
import ctm.mptc.kh.ecommerce.order.restapi.mapper.OrderWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")

// Constructor based dependency injection (Lombok)
@RequiredArgsConstructor
public class OrderCommandController {

    // Declare required dependencies
    private final CreateOrderUseCase createOrderUseCase;
    private final OrderWebMapper orderWebMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderCreateResponse createOrder(
            @Valid @RequestBody OrderCreateRequest orderCreateRequest
    ) {
        // Mapping logic
        CreateOrderCommand createOrderCommand = orderWebMapper.orderCreateRequestToCreateOrderCommand(orderCreateRequest);
        // UseCase logic
        CreateOrderResult createOrderResult = createOrderUseCase.execute(createOrderCommand);
        // Mapping logic
        return orderWebMapper.createOrderResultToOrderCreateResponse(createOrderResult);
    }
}