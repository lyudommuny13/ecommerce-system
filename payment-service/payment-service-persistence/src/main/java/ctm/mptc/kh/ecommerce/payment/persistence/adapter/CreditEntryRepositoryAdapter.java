package ctm.mptc.kh.ecommerce.payment.persistence.adapter;

import ctm.mptc.kh.ecommerce.domain.valueobject.CustomerId;
import ctm.mptc.kh.ecommerce.payment.domain.entity.CreditEntry;
import ctm.mptc.kh.ecommerce.payment.domain.port.output.CreditEntityRepository;
import ctm.mptc.kh.ecommerce.payment.persistence.entity.CreditEntryEntity;
import ctm.mptc.kh.ecommerce.payment.persistence.mapper.CreditEntryPersistenceMapper;
import ctm.mptc.kh.ecommerce.payment.persistence.repository.CreditEntryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreditEntryRepositoryAdapter implements CreditEntityRepository {
    private final CreditEntryJpaRepository creditEntryJpaRepository;
    private final CreditEntryPersistenceMapper creditEntryPersistenceMapper;

    @Override
    public CreditEntry findByCustomerId(CustomerId customerId) {
        return creditEntryJpaRepository.findByCustomerId(customerId.value())
                .map(creditEntryPersistenceMapper::creditEntryEntityToCreditEntry)
                .orElse(null);
    }

    @Override
    public CreditEntry save(CreditEntry creditEntry) {
        CreditEntryEntity creditEntryEntity = creditEntryPersistenceMapper.creditEntryToCreditEntryEntity(creditEntry);
        CreditEntryEntity savedCreditEntryEntity = creditEntryJpaRepository.save(creditEntryEntity);
        return creditEntryPersistenceMapper.creditEntryEntityToCreditEntry(savedCreditEntryEntity);
    }
}
