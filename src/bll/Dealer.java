package bll;

import be.Card;
import be.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Dealer {
    private Deck deck;
    private Stack<Card> shuffledDeck;
    private List<Card> cardsCopy;
    private Random random;
    private int index;

    public Dealer(Deck deck) {
        random = new Random();

        this.deck = deck;
        index = deck.getCards().size();

        cardsCopy = new ArrayList<>();
        cardsCopy.addAll(deck.getCards());
    }

    public Deck getDeck() {
        return deck;
    }

    public Card getCard() {
        return shuffledDeck.pop();
    }

    public boolean hasEmptyDeck() {
        return shuffledDeck.empty();
    }

    public Deck shuffle() {
        shuffledDeck = new Stack<>();

        while (index > 0) {
            Card c = cardsCopy.get(random.nextInt(index));
            shuffledDeck.push(c);
            cardsCopy.remove(c);
            index--;
        }

        resetDeck();

        return new Deck(shuffledDeck);
    }

    private void resetDeck() {
        index = deck.getCards().size();
        cardsCopy.addAll(deck.getCards());
    }
}
