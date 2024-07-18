package az.company.cardproject.scheduler;

import az.company.cardproject.client.CashbackClient;
import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.dao.entity.Transaction;
import az.company.cardproject.dao.repository.CardRepository;
import az.company.cardproject.dao.repository.TransactionRepository;
import az.company.cardproject.service.CashbackService;
import az.company.cardproject.service.TransactionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class CashbackScheduler {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final CashbackService cashbackService;

    public CashbackScheduler(TransactionRepository transactionRepository, CardRepository cardRepository, CashbackService cashbackService) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
        this.cashbackService = cashbackService;
    }
    @Scheduled(cron = "0 15 19 * * ?")
    public void calculateAndCreditCashback(){
        LocalDate localDate=LocalDate.now();
        List<Transaction>transactions=transactionRepository.findByTransactionDate(localDate);
        transactions.forEach(transaction -> {
            if (transaction.getHasCashback()){
                Card card=transaction.getCard();
                BigDecimal transactionAmount=transaction.getAmount();
                BigDecimal cashbackAmount=cashbackService.calculateCashback(transactionAmount);
                card.setBalance(card.getBalance().add(cashbackAmount));
                cardRepository.save(card);
                System.out.println("Cashback amount  "+cashbackAmount+"add this card  :"+card.getId());
                transaction.setHasCashback(false);
                transactionRepository.save(transaction);



            }
        });
    }
}
