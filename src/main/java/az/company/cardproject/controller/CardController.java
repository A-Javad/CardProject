package az.company.cardproject.controller;

import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.model.dto.CardDTO;
import az.company.cardproject.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public CardDTO getCardsById(@PathVariable Long id){
        return cardService.getCardsById(id);
    }
    @PostMapping("/cards")
    public void saveCards(@RequestBody Card card){
        cardService.saveCards(card);
    }
    @DeleteMapping("/cards/{id}")
    public void deleteCardsById(@PathVariable Long id){
        cardService.deleteCards(id);
    }


}
