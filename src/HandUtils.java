import java.util.*;

public class HandUtils {

    // converting rank to number for scoring yk
    public static int getCardValue(String rank) {
        switch (rank) {
            case "J":
            case "Q":
            case "K":
                return 10;
            case "A":
                return 11;
            default:
                return Integer.parseInt(rank);
        }
    }

    // calculates suit totals for a hand
    public static Map<String, Integer> suitTotals(List<Card> hand) {
        Map<String, Integer> totals = new HashMap<>();
        totals.put("Hearts", 0);
        totals.put("Diamonds", 0);
        totals.put("Clubs", 0);
        totals.put("Spades", 0);


        for (Card c : hand) {
            int value = getCardValue(c.getRank());
            totals.put(c.getSuit(), totals.get(c.getSuit()) + value);
        }


        return totals;
    }


    // finds the highest scoring suit name
    public static String bestSuit(Map<String, Integer> totals) {
        int hearts = totals.get("Hearts");
        int diamonds = totals.get("Diamonds");
        int clubs = totals.get("Clubs");
        int spades = totals.get("Spades");


        int maxTotal = Math.max(Math.max(hearts, diamonds), Math.max(clubs, spades));


        String bestSuit = "";
        if (maxTotal == hearts) bestSuit = "Hearts";
        if (maxTotal == diamonds) bestSuit = "Diamonds";
        if (maxTotal == clubs) bestSuit = "Clubs";
        if (maxTotal == spades) bestSuit = "Spades";


        return bestSuit;
    }


    // returns the max suit total number
    public static int bestSuitTotal(Map<String, Integer> totals) {
        return Math.max(
                Math.max(totals.get("Hearts"), totals.get("Diamonds")),
                Math.max(totals.get("Clubs"), totals.get("Spades"))
        );
    }


    // Level 4 scoring: best suit total +5 if best suit = bonus suit
    public static int scoreHand(List<Card> hand, String bonusSuit) {
        Map<String, Integer> totals = suitTotals(hand);
        String bestSuit = bestSuit(totals);
        int score = bestSuitTotal(totals);


        if (bestSuit.equalsIgnoreCase(bonusSuit)) {
            score += 5;
        }


        return score;
    }


    // shows A–E labelled hand
    public static void printLetteredHand(List<Card> hand) {
        System.out.println("A: " + hand.get(0));
        System.out.println("B: " + hand.get(1));
        System.out.println("C: " + hand.get(2));
        System.out.println("D: " + hand.get(3));
        System.out.println("E: " + hand.get(4));
    }


    //_____START OF LEVEL 7_____// - Computer Player
    // decide which cards to drop (return indexes)
    // drop cards not in bestSuit, up to 4
    public static List<Integer> getComputerCardDrops(List<Card> hand, String bestSuit) {
        List<Integer> dropIndexes = new ArrayList<>();


        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(i);


            if (!c.getSuit().equalsIgnoreCase(bestSuit)) {
                // if the card isnt best suit, consider dropping it
                dropIndexes.add(i);
            }
        }


        // like a player it can only drop 4 cards
        if (dropIndexes.size() > 4) {
            return new ArrayList<>(dropIndexes.subList(0, 4));
        }


        return dropIndexes;
    }
}
