package ctm.mptc.kh.ecommerce.order.persistence.mapper;

import ctm.mptc.kh.ecommerce.order.domain.entity.Customer;
import ctm.mptc.kh.ecommerce.order.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {

    @Mapping(source = "id", target = "id.value")
    Customer customerEntityToCustomer(CustomerEntity customerEntity);

}
