package az.company.cardproject.service;

import az.company.cardproject.client.CashbackClient;
import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.dao.entity.Transaction;
import az.company.cardproject.dao.repository.CardRepository;
import az.company.cardproject.dao.repository.TransactionRepository;
import az.company.cardproject.mapper.TransactionMapper;
import az.company.cardproject.model.dto.TransactionDTO;
import az.company.cardproject.model.exception.CardNotFoundException;
import az.company.cardproject.model.exception.InsufficientBalanceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final CashbackClient cashbackClient;
    private final TransactionMapper transactionMapper;
    public TransactionService(TransactionRepository transactionRepository, CardRepository cardRepository, CashbackClient cashbackClient, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
        this.cashbackClient = cashbackClient;
        this.transactionMapper = transactionMapper;
    }
    @Transactional
    public TransactionDTO performTransaction(Long cardId, String type, BigDecimal amount, Boolean hasCashback) throws InsufficientBalanceException {
        if (amount.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("Transaction amount must be positive");
        }
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException("Card not found with id"+cardId));
        switch (type.toUpperCase()) {
            case "DEBIT":
                if (card.getBalance().compareTo(amount)<0){
                    throw new InsufficientBalanceException("Insufficient balance for transaction");
                }
                card.setBalance(card.getBalance().subtract(amount));
                break;
            case "CREDIT":
                card.setBalance(card.getBalance().add(amount));
                break;
            default:
                throw new IllegalArgumentException("Invalid transaction type: " + type);
        }
        Transaction transaction = createTransaction(card, type, amount, hasCashback);
        if (Boolean.TRUE.equals(hasCashback)) {
            BigDecimal cashbackAmount = cashbackClient.fetchCashbackAmount(amount);
            card.setBalance(card.getBalance().add(cashbackAmount));
            System.out.println("Cashback Amount: " + cashbackAmount);
        }
        transactionRepository.save(transaction);
        cardRepository.save(card);
        return transactionMapper.transactionToTransactionDTO(transaction);
    }
    private Transaction createTransaction(Card card,String type,BigDecimal amount,Boolean hasCashback){
        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setHasCashback(hasCashback);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setCard(card);
        return transaction;
    }


}
