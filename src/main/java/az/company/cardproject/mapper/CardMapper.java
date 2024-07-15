package az.company.cardproject.mapper;

import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.model.dto.CardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE= Mappers.getMapper(CardMapper.class);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "pan", source = "pan")
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "balance", source = "balance")
    CardDTO cardTOCardDTO(Card card);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "pan", source = "pan")
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "balance", source = "balance")
    Card cardDTOToCard(CardDTO cardDTO);
}
