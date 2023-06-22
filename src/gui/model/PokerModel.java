package gui.model;

import be.Card;
import be.Deck;
import bll.Dealer;
import javafx.scene.image.Image;

import java.util.List;

public class PokerModel {
    private Dealer dealer;

    public PokerModel() {
        dealer = new Dealer(new Deck());
    }

    public List<Card> getCards() {
        return dealer.getDeck().getCards();
    }

    public Card getCard() {
        return dealer.getCard();
    }

    public Image getCardImage(Card card) {
        return dealer.getDeck().getCardImage(card);
    }

    public boolean hasEmptyDeck() {
        return dealer.hasEmptyDeck();
    }

    public void shuffle() {
        dealer.shuffle();
    }
}
