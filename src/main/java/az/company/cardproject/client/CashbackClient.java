package az.company.cardproject.client;

import az.company.cardproject.model.dto.CashbackDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class CashbackClient {
    private final RestTemplate restTemplate;
    private final String cashbackApiUrl;
    public CashbackClient(RestTemplate restTemplate, @Value("${cashback.api.url}") String cashbackApiUrl) {
        this.restTemplate = restTemplate;
        this.cashbackApiUrl = cashbackApiUrl;
    }
    public BigDecimal fetchCashbackAmount(BigDecimal transactionAmount) {
        String url = cashbackApiUrl + "?amount=" + transactionAmount;
        CashbackDTO response = restTemplate.getForObject(url, CashbackDTO.class);
        return response.getCashback();
    }
}
