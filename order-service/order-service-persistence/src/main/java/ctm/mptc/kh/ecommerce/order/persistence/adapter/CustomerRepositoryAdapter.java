package ctm.mptc.kh.ecommerce.order.persistence.adapter;

import ctm.mptc.kh.ecommerce.order.domain.entity.Customer;
import ctm.mptc.kh.ecommerce.order.domain.port.output.CustomerRepository;
import ctm.mptc.kh.ecommerce.order.persistence.mapper.CustomerPersistenceMapper;
import ctm.mptc.kh.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import ctm.mptc.kh.ecommerce.order.persistence.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerPersistenceMapper customerPersistenceMapper;

    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId)
                .map(customerPersistenceMapper::customerEntityToCustomer);
    }
}
