package az.company.cardproject.mapper;

import az.company.cardproject.dao.entity.Transaction;
import az.company.cardproject.model.dto.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "hasCashback", source = "hasCashback")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "cardId", source = "card.id")
    TransactionDTO transactionToTransactionDTO(Transaction transaction);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "hasCashback", source = "hasCashback")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "card.id", source = "cardId")
    Transaction transactionDTOToTransaction(TransactionDTO transactionDTO);
}
