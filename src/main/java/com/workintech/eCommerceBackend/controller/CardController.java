package com.workintech.eCommerceBackend.controller;

import com.workintech.eCommerceBackend.entity.Card;
import com.workintech.eCommerceBackend.service.CardService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cards")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }
    @PostMapping("/{cardId}/addProduct/{productId}")
    public Card addProductToCard(@PathVariable Long cardId, @PathVariable Long productId) {
        return cardService.addProductToCard(cardId, productId);
    }

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{cardId}")
    public Card getCardById(@PathVariable Long cardId) {
        return cardService.getCardById(cardId);
    }

    @PostMapping
    public Card saveCard(@RequestBody Card card) {
        return cardService.saveCard(card);
    }

    @DeleteMapping("/{cardId}")
    public void deleteCardById(@PathVariable Long cardId) {
        cardService.deleteCardById(cardId);
    }
}
