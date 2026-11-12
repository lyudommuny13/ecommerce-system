package ctm.mptc.kh.ecommerce.customer.persistence.adapter;

import ctm.mptc.kh.ecommerce.core.PageResponse;
import ctm.mptc.kh.ecommerce.customer.domain.dto.CustomerResult;
import ctm.mptc.kh.ecommerce.customer.domain.entity.Customer;
import ctm.mptc.kh.ecommerce.customer.domain.port.ouput.CustomerRepository;
import ctm.mptc.kh.ecommerce.customer.persistence.entity.CustomerEntity;
import ctm.mptc.kh.ecommerce.customer.persistence.mapper.CustomerPersistenceMapper;
import ctm.mptc.kh.ecommerce.customer.persistence.repository.CustomerJpaRepository;
import ctm.mptc.kh.ecommerce.domain.valueobject.CustomerId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerPersistenceMapper customerPersistenceMapper;

    @Override
    public PageResponse<CustomerResult> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerEntity> customerEntityPage =  customerJpaRepository.findAll(pageable);
        Page<CustomerResult> customerResultPage = customerEntityPage.map(customerPersistenceMapper::toCustomerResult);
        return PageResponse.<CustomerResult>builder()
                .items(customerResultPage.getContent())
                .page(customerResultPage.getNumber())
                .size(customerResultPage.getSize())
                .totalPages(customerResultPage.getTotalPages())
                .totalElements(customerResultPage.getTotalElements())
                .hasNext(customerResultPage.hasNext())
                .build();
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity savedEntity = customerJpaRepository.save(
                customerPersistenceMapper.customerToCustomerEntity(customer));
        return customerPersistenceMapper.customerEntityToCustomer(savedEntity);
    }

    @Override
    public Optional<Customer> findById(CustomerId customerId) {
        return customerJpaRepository.findById(customerId.value())
                .map(customerPersistenceMapper::customerEntityToCustomer);
    }

    @Override
    public boolean existsByUsername(String username) {
        return customerJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerJpaRepository.existsByEmail(email);
    }
}
