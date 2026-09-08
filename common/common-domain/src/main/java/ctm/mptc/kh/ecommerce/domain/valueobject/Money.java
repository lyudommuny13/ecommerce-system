package ctm.mptc.kh.ecommerce.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount)
{
    public  static  final  Money ZERO = new Money(BigDecimal.ZERO);

    // Validate amount > 0
    public boolean isGreaterThanZero() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    // Validate amount > moneyInput
    public boolean isGreaterThan(Money money) {
        return amount.compareTo(money.amount) > 0;
    }

    // បន្ថែមទឹកលុយ
    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.amount)));
    }

    // ដកទឹកលុយ
    public Money subtract(Money money) {
        return new Money(setScale(this.amount.subtract(money.amount)));
    }

    // គុណលុយ (គុណចំនួនដង)
    public Money multiply(int multiplier) {
        return new Money(setScale(this.amount.multiply(BigDecimal.valueOf(multiplier))));
    }

    private BigDecimal setScale(BigDecimal inputAmount) {
        return inputAmount.setScale(2, RoundingMode.HALF_EVEN);
    }
}
