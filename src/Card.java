
public class Card {


    // fields
    private final String suit;
    private final String rank;


    // will stand out in console
    private static final String RED = "[31m";
    private static final String BLACK = "[30m";
    private static final String RESET = "[0m";


    // constructor
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }


    // Getter methods
    public String getSuit() {
        return suit;
    }


    public String getRank() {
        return rank;
    }


    @Override       //how it'll display
    public String toString() {
        if (suit.equals("Hearts") || suit.equals("Diamonds")) {
            return RED + rank + " of " + suit + RESET;
        }
        
        return BLACK + rank + " of " + suit + RESET;
    }
}


/* Each card is gonna have a rank 2 - And a suit alongside it
* so toString() prints the card e.g ('A of Spades')
*
* card represents one individual playing card in the game
* just a blueprint
*
*/