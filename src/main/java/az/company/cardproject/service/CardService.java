package az.company.cardproject.service;

import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.dao.repository.CardRepository;
import az.company.cardproject.model.dto.CardDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    public List<CardDTO>getAllCards(){
        List<Card>cards=cardRepository.findAll();
        return cards.stream().map(card -> new CardDTO(
                card.getId(),
                card.getPan(),
                card.getCustomerId(),
                card.getBalance()
        )).collect(Collectors.toList());
    }
    public CardDTO getCardsById(Long id){
        Optional<Card> cards=cardRepository.findById(id);
        return cards.map(card -> new CardDTO(
                card.getId(),
                card.getPan(),
                card.getCustomerId(),
                card.getBalance()
        )).orElseThrow(null);
    }
    public void saveCards(Card card){
        card.setCreatedAt(LocalDateTime.now());
         cardRepository.save(card);
    }


}
