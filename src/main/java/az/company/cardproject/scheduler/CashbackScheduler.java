package az.company.cardproject.scheduler;

import az.company.cardproject.service.CashbackService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CashbackScheduler {
    private final CashbackService cashbackService;

    public CashbackScheduler(CashbackService cashbackService) {
        this.cashbackService = cashbackService;
    }
    @Scheduled(cron = "0 0 2 * * ?")
    public void calculateAndCreditCashback(){
        cashbackService.calculateCashback()
    }
}
