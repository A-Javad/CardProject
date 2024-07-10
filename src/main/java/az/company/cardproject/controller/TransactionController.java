package az.company.cardproject.controller;

import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/cards")
public class TransactionController {

    private final   TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{id}/transaction")
    public Card performTransaction(@PathVariable Long id, @RequestBody Map<String,Object>payload){
        String type = (String) payload.get("type");
        BigDecimal amount = new BigDecimal(String.valueOf(payload.get("amount")));
        Boolean hasCashback = (Boolean) payload.get("hasCashback");
        return transactionService.performTransaction(id,type,amount,hasCashback);
    }
}
