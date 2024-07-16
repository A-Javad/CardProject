package az.company.cardproject.mapper;

import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.model.dto.CardDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    public  CardDTO cardToCardDTO(Card card) {
        CardDTO dto = new CardDTO();
        dto.setId(card.getId());
        dto.setPan(card.getPan());
        dto.setCustomerId(card.getCustomerId());
        dto.setBalance(card.getBalance());
        return dto;
    }

    public  Card cardDTOToCard(CardDTO cardDTO) {
        Card card = new Card();
        card.setId(cardDTO.getId());
        card.setPan(cardDTO.getPan());
        card.setCustomerId(cardDTO.getCustomerId());
        card.setBalance(cardDTO.getBalance());
        return card;
    }
}
