package ctm.mptc.kh.ecommerce.customer.domain.usecase;

import ctm.mptc.kh.ecommerce.core.PageResponse;
import ctm.mptc.kh.ecommerce.customer.domain.dto.CustomerResult;
import ctm.mptc.kh.ecommerce.customer.domain.dto.GetCustomersQuery;
import ctm.mptc.kh.ecommerce.customer.domain.port.ouput.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllCustomersUseCase {

    private final CustomerRepository customerRepository;

    public PageResponse<CustomerResult> execute(GetCustomersQuery query) {
        log.info("Execute GetAllCustomersUseCase " + query);
        return customerRepository.findAll(query.page(), query.size());
    };
}
