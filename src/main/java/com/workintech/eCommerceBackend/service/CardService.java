package com.workintech.eCommerceBackend.service;

import com.workintech.eCommerceBackend.entity.Card;
import com.workintech.eCommerceBackend.entity.Product;
import com.workintech.eCommerceBackend.exception.CardNotFoundException;
import com.workintech.eCommerceBackend.exception.CardOrProductNotFoundException;
import com.workintech.eCommerceBackend.repository.CardRepository;
import com.workintech.eCommerceBackend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {


    private final CardRepository cardRepository;
    private final ProductRepository productRepository;
    @Autowired
    public CardService(CardRepository cardRepository, ProductRepository productRepository) {
        this.cardRepository = cardRepository;
        this.productRepository = productRepository;
    }
    public Card addProductToCard(Long cardId, Long productId) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalCard.isPresent() && optionalProduct.isPresent()) {
            Card card = optionalCard.get();
            Product product = optionalProduct.get();

            card.getProducts().add(product);
            product.getCards().add(card);

            return cardRepository.save(card);
        } else {
            throw new CardOrProductNotFoundException("Card or Product not found" + cardId + " " + productId, HttpStatus.NOT_FOUND);
        }
    }
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long cardId) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);

        return optionalCard
                .orElseThrow(() -> new CardNotFoundException("Card not found with ID: " + cardId, HttpStatus.NOT_FOUND));
    }
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    public Card deleteCardById(Long cardId) {
    Optional<Card> optionalCard = cardRepository.findById(cardId);
        return optionalCard
                .orElseThrow(() -> new CardNotFoundException("Card not found with ID: " + cardId, HttpStatus.NOT_FOUND));
    }

}
