package az.company.cardproject.controller;

import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.model.dto.CardDTO;
import az.company.cardproject.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @GetMapping("/cards")
    public List<CardDTO>getAllCards(){
        return cardService.getAllCards();
    }
    @GetMapping("/cards/{id}")
    public CardDTO getCardsById( @PathVariable Long id){
        return cardService.getCardsById(id);
    }
    @PostMapping("/cards")
    public void saveCards(@RequestBody Card card){
        cardService.saveCards(card);
    }


}
