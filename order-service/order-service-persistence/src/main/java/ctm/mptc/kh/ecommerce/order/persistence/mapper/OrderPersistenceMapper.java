package ctm.mptc.kh.ecommerce.order.persistence.mapper;

import ctm.mptc.kh.ecommerce.order.domain.entity.Business;
import ctm.mptc.kh.ecommerce.order.domain.entity.Customer;
import ctm.mptc.kh.ecommerce.order.persistence.entity.BusinessEntity;
import ctm.mptc.kh.ecommerce.order.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderPersistenceMapper {

    @Mapping(source = "id", target = "id.value")
    Customer customerEntityToCustomer(CustomerEntity customerEntity);

    @Mapping(source = "businessId", target = "id.value")
    Business businessEntityToBusiness(BusinessEntity businessEntity);
}
