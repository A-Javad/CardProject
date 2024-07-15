package az.company.cardproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CashbackController {
    @GetMapping("/cashback")
    public Map<String, BigDecimal> getCashback(@RequestParam BigDecimal amount) {
        Map<String, BigDecimal> response = new HashMap<>();
        response.put("cashback", amount.multiply(BigDecimal.valueOf(0.005))); // 0.5% cashback
        return response;
    }
}
