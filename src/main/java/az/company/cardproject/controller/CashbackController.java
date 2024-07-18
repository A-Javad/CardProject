package az.company.cardproject.controller;

import az.company.cardproject.scheduler.CashbackScheduler;
import az.company.cardproject.service.CashbackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CashbackController {
    private final CashbackService cashbackService;
    private final CashbackScheduler cashbackScheduler;

    public CashbackController(CashbackService cashbackService, CashbackScheduler cashbackScheduler) {
        this.cashbackService = cashbackService;
        this.cashbackScheduler = cashbackScheduler;
    }

    @GetMapping("/cashback")
    public Map<String, BigDecimal> getCashback(@RequestParam BigDecimal amount) {
        BigDecimal cashbackAmount=cashbackService.calculateCashback(amount);
        Map<String,BigDecimal>response = new HashMap<>();
        response.put("cashback",cashbackAmount);
        return response;
    }

}
