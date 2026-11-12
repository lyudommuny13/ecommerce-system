package ctm.mptc.kh.ecommerce.customer.domain.port.ouput;

import ctm.mptc.kh.ecommerce.core.PageResponse;
import ctm.mptc.kh.ecommerce.customer.domain.dto.CustomerResult;
import ctm.mptc.kh.ecommerce.customer.domain.entity.Customer;
import ctm.mptc.kh.ecommerce.domain.valueobject.CustomerId;

import java.util.Optional;

public interface CustomerRepository {

    PageResponse<CustomerResult> findAll(int page, int size);

    Customer save(Customer customer);
    Optional<Customer> findById(CustomerId customerId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
