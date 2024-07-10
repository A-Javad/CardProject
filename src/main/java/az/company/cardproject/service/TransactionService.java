package az.company.cardproject.service;

import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.dao.entity.Transaction;
import az.company.cardproject.dao.repository.CardRepository;
import az.company.cardproject.dao.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;


    private final   CardRepository cardRepository;

    public TransactionService(TransactionRepository transactionRepository, CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
    }


    @Transactional
    public Card performTransaction(Long cardId, String type, BigDecimal amount,Boolean hasCashback){
        Card card=cardRepository.findById(cardId).orElseThrow(() -> new RuntimeException("Card not found"));
        if ("DEBIT".equalsIgnoreCase(type)){
            card.setBalance(card.getBalance().subtract(amount));
        } else if ("CREDIT".equalsIgnoreCase(type)) {
            card.setBalance(card.getBalance().add(amount));
        }else {
            throw new IllegalArgumentException("Invalid Transaction Type");
        }
        Transaction transaction= new Transaction();
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setHasCashback(hasCashback);
        transaction.setCreatedAt(Timestamp.from(Instant.now()));
        transaction.setCard(card);
        transactionRepository.save(transaction);
        cardRepository.save(card);
        return card;

    }


}
