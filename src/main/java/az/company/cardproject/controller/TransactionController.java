package az.company.cardproject.controller;

import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.model.dto.TransactionDTO;
import az.company.cardproject.service.TransactionService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TransactionDTO> performTransaction(@PathVariable Long id, @RequestBody Map<String,Object>payload){
        String type = (String) payload.get("type");
        BigDecimal amount = new BigDecimal(String.valueOf(payload.get("amount")));
        Boolean hasCashback = (Boolean) payload.get("hasCashback");
        TransactionDTO transactionDTO=transactionService.performTransaction(id,type,amount,hasCashback);
        return ResponseEntity.ok(transactionDTO);
    }
}
