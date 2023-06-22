package be;

import be.enums.Suit;
import be.enums.Value;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private Map<Card, Image> cardImageMap = new HashMap<>();
    private int index = 1;

    /**
     * Constructor for standard 52 cards deck
     */
    public Deck() {
        for(Suit s : Suit.values()) {
            for(Value v : Value.values()) {
                Card c = new Card(s, v);
                cards.add(c);
                cardImageMap.put(c, new Image("/cardsNumbered/" + index + ".png"));
                index++;
            }
        }
    }

    /**
     * Constructor for non-standard deck of cards
     * @param cards
     */
    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Image getCardImage(Card card) {
        return cardImageMap.get(card);
    }

    @Override
    public String toString() {
        return "Deck of " + cards.size() + " cards\n"
                + cards;
    }
}
