package be;

import be.enums.Suit;
import be.enums.Value;

public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public int getIndex(Card card) {


        return 0;
    }

    @Override
    public String toString() {
        return "" + value + suit;
    }
}
