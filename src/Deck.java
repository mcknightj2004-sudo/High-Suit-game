
import java.util.*;

public class Deck {
    private final List<Card> cards; // list to hold 52 cards
    private int currentCard = 0; // how many dealt so far


    // constructor will build a new deck of the 52 cards and shuffle it
    public Deck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};


        cards = new ArrayList<>(); // create empty list for card objs


        for (String suit : suits) { // take one suit at a time from suits array
            for (String rank : ranks) { // goes through every rank and applies it
                cards.add(new Card(rank, suit)); // creates every combo
            }
        }
        shuffle(); // shuffle afterwards ofc
    }


    public void shuffle() {
    // randomly rearranges list and will reset card back to the top
        Collections.shuffle(cards);
        currentCard = 0;
    }


    public Card dealCard() {
    // deal one
        if (currentCard < cards.size()) {
            return cards.get(currentCard++);
        }
        return null; // no cards left
    }


    public int remaining() {
        return cards.size() - currentCard;
    }
}


/*
creates 52 cards objects and puts them in arraylist.
shuffles them.
deals them and keeps track
*/

