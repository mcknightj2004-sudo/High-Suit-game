
import java.util.*;

//_____LEVEL 8_____//
//structured replay record

public class ReplayGame {

    private final int round;
    private final String playerName;


    private final List<Card> initialHand;
    private final String bonusSuit;


    // what the player did (letters for human and an index list for tha computer)
    private final String actionDescription;


    private final List<Card> finalHand;
    private final int score;


    public ReplayGame(int round,
                      String playerName,
                      List<Card> initialHand,
                      String bonusSuit,
                      String actionDescription,
                      List<Card> finalHand,
                      int score) {
        this.round = round;
        this.playerName = playerName;
        this.initialHand = initialHand;
        this.bonusSuit = bonusSuit;
        this.actionDescription = actionDescription;
        this.finalHand = finalHand;
        this.score = score;
    }


// printed in a readable way
    public void print() {
        System.out.println("Round " + round + " - " + playerName);
        System.out.println("Initial hand: " + initialHand);
        System.out.println("Bonus suit: " + bonusSuit);
        System.out.println("Action: " + actionDescription);
        System.out.println("Final hand: " + finalHand);
        System.out.println("Score: " + score);
    }
}
