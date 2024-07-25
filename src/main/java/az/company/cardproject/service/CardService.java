package az.company.cardproject.service;
import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.dao.repository.CardRepository;
import az.company.cardproject.mapper.CardMapper;
import az.company.cardproject.model.dto.CardDTO;
import az.company.cardproject.model.exception.CardNotValidException;

import az.company.cardproject.specification.CardSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {
    private final CardMapper cardMapper;
    private final ValidationService validationService;
    private final CardRepository cardRepository;

    public CardService(CardMapper cardMapper, ValidationService validationService, CardRepository cardRepository) {
        this.cardMapper = cardMapper;
        this.validationService = validationService;
        this.cardRepository = cardRepository;
    }

    public List<CardDTO>getAllCards(int page,int size){
        Pageable pageable= PageRequest.of(page, size);
        List<Card> cards = cardRepository.findAll();
        return cards.stream()
                .map(cardMapper::cardToCardDTO)
                .collect(Collectors.toList());
    }
    public CardDTO getCardsById(Long id){
        Card card = cardRepository.findById(id)
                .orElseThrow(() ->new RuntimeException("Card not found with id"));
        return cardMapper.cardToCardDTO(card);

    }
    public List<CardDTO>getCardsFiltered(String customerId,BigDecimal minBalance,BigDecimal maxBalance){
        Specification<Card>specification=Specification.where(null);
        if (customerId !=null && !customerId.isEmpty()){
            specification=specification.and(CardSpecification.hasCustomerId(customerId));
        }
        if (minBalance!=null){
            specification=specification.and(CardSpecification.hasBalanceGreaterThan(minBalance));
        }
        if (maxBalance!=null){
            specification=specification.and(CardSpecification.hasBalanceLessThan(maxBalance));
        }
        List<Card> cards=cardRepository.findAll(specification);
        return cards.stream()
                .map(cardMapper::cardToCardDTO)
                .collect(Collectors.toList());
    }
    public void saveCards(Card card){
        if (validationService.isCardNoValid(card.getPan())) {
            card.setCreatedAt(LocalDateTime.now());
            card.setBalance(BigDecimal.valueOf(0));
            cardRepository.save(card);
        }else {
            throw new CardNotValidException("Enter card number wrong");
        }


    }
    public void deleteCards(Long id){
        try {
            cardRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Cars not found with id");
        }


    }
}
