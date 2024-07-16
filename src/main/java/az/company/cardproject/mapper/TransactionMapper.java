package az.company.cardproject.mapper;

import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.dao.entity.Transaction;
import az.company.cardproject.model.dto.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionDTO transactionToTransactionDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setType(transaction.getType());
        dto.setAmount(transaction.getAmount());
        dto.setHasCahback(transaction.getHasCashback());
        dto.setCreatedAt(transaction.getCreatedAt());
        if (transaction.getCard() != null) {
            dto.setCardId(transaction.getCard().getId());
        }
        return dto;
    }

    public Transaction transactionDTOToTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDTO.getId());
        transaction.setType(transactionDTO.getType());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setHasCashback(transactionDTO.getHasCahback());
        transaction.setCreatedAt(transactionDTO.getCreatedAt());
        if (transactionDTO.getCardId() != null) {
            Card card = new Card();
            card.setId(transactionDTO.getCardId());
            transaction.setCard(card);
        }
        return transaction;
    }
}
