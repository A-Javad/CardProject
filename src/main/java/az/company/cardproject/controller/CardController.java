package az.company.cardproject.controller;

import az.company.cardproject.dao.entity.Card;
import az.company.cardproject.model.dto.CardDTO;
import az.company.cardproject.service.CardService;
import org.hibernate.query.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping()
public class CardController {
    private final CardService cardService;
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }
    @GetMapping("/cards")
    public List<CardDTO> getAllCards(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size){
        return cardService.getAllCards(page, size);
    }
    @GetMapping("/cards/filtered")
    public List<CardDTO>getFilteredCards(@RequestParam(required = false) String customerId,
                                      @RequestParam(required = false) BigDecimal minBalance,
                                      @RequestParam(required = false) BigDecimal maxBalance){
        return cardService.getCardsFiltered(customerId,minBalance,maxBalance);
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
