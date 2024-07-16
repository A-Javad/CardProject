package az.company.cardproject.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class CashbackService {
    public BigDecimal calculateCashback(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(0.005));
    }
}
