package ctm.mptc.kh.ecommerce.customer.restapi.mapper;

import ctm.mptc.kh.ecommerce.core.PageResponse;
import ctm.mptc.kh.ecommerce.customer.domain.dto.*;
import ctm.mptc.kh.ecommerce.customer.restapi.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {
    CreateCustomerCommand customerCreateRequestToCreateCustomerCommand(CustomerCreateRequest customerCreateRequest);
    CustomerCreateResponse createCustomerResultToCustomerCreateResponse(CreateCustomerResult createCustomerResult);

    // customerId comes from the path, the rest from the request body
    @Mapping(source = "customerId", target = "customerId")
    UpdateCustomerCommand customerUpdateRequestToUpdateCustomerCommand(UUID customerId, CustomerUpdateRequest customerUpdateRequest);
    CustomerUpdateResponse updateCustomerResultToCustomerUpdateResponse(UpdateCustomerResult updateCustomerResult);

    PageResponse<CustomerResponse> toCustomerResponse(PageResponse<CustomerResult> result);
    CustomerResponse toCustomerResponse(CustomerResult result);
}
