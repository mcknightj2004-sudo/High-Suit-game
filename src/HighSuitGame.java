import java.util.*;

public class HighSuitGame {     //handles user input and round loop and end features

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean playAgain = true;   // Level 8 replay + play again

        HighScoreTable highScores = new HighScoreTable("highscores.txt");
        highScores.load();

        while (playAgain) {

            Deck deck = new Deck();   // ONE deck per game
            List<ReplayGame> replay = new ArrayList<>();

            System.out.println("Welcome to HighSuit!");

            int numPlayers = readIntInRange(input,
                    "Enter number of players (1 or 2): ", 1, 2);

            List<Player> players = new ArrayList<>();
            for (int i = 1; i <= numPlayers; i++) {
                System.out.print("Enter name for Player " + i + ": ");
                players.add(new Player(input.nextLine()));
            }

            int numRounds = readIntInRange(input,
                    "Enter the number of rounds you would like to play (1-3): ", 1, 3);

            // _____ROUNDS LOOP_____
            for (int round = 1; round <= numRounds; round++) {

                System.out.println("\n-------- ROUND " + round + " --------");

                List<List<Card>> hands = new ArrayList<>();
                List<List<Card>> initialHands = new ArrayList<>();

                
                for (Player p : players) {

                    List<Card> hand = new ArrayList<>();

                    System.out.println("\n" + p.getName() + "'s hand:");
                    for (int i = 0; i < 5; i++) {
                        Card c = deck.dealCard();
                        hand.add(c);
                        System.out.println(c);
                    }

                    hands.add(hand);
                    initialHands.add(new ArrayList<>(hand)); 
                }

                // _____LEVEL 2: bonus suit_____
                List<String> bonusSuits = new ArrayList<>();

                for (int i = 0; i < players.size(); i++) {

                    Player p = players.get(i);
                    List<Card> hand = hands.get(i);

                    String bestSuit =
                            HandUtils.bestSuit(HandUtils.suitTotals(hand));

                    String bonus;

                    if (p.isComputer()) {
                        bonus = bestSuit;
                        System.out.println("\nComputer automatically selects bonus suit: " + bonus);
                    } else {
                        bonus = readSuit(input,
                                "\n" + p.getName() +
                                ", Choose your bonus suit (Hearts / Diamonds / Clubs / Spades): ");
                    }

                    bonusSuits.add(bonus);
                }
                //alot of validation going on
                // _____LEVEL 3: Swaps_____
                List<String> actions = new ArrayList<>();

                for (int i = 0; i < players.size(); i++) {

                    Player p = players.get(i);
                    List<Card> hand = hands.get(i);

                    System.out.println("\n" + p.getName() + "'s current hand:");
                    HandUtils.printLetteredHand(hand);

                    if (p.isComputer()) {

                        String bestSuit =
                                HandUtils.bestSuit(HandUtils.suitTotals(hand));

                        List<Integer> drops =
                                HandUtils.getComputerCardDrops(hand, bestSuit);

                        System.out.println("\nComputer decides to swap " + drops.size() + " cards.");

                        for (int idx : drops) {
                            Card newCard = deck.dealCard();
                            System.out.println("Computer replaces " + hand.get(idx) + " with " + newCard);
                            hand.set(idx, newCard);
                        }

                        actions.add("Dropped indexes " + drops);

                    } else {

                        int swaps = readIntInRange(input,
                                "How many cards do you want to swap? (0-4): ", 0, 4);

                        Set<Integer> used = new HashSet<>();
                        List<String> dropped = new ArrayList<>();

                        for (int s = 0; s < swaps; s++) {

                            String letter = readLetterAtoE(input,
                                    "Enter card letter to drop (A-E): ");
                            int index = letter.charAt(0) - 'A';

                            while (used.contains(index)) {
                                letter = readLetterAtoE(input,
                                        "You already chose that card. Pick another (A-E): ");
                                index = letter.charAt(0) - 'A';
                            }

                            used.add(index);
                            dropped.add(letter);

                            Card newCard = deck.dealCard();
                            hand.set(index, newCard);
                            System.out.println("Card replaced with: " + newCard);
                        }

                        actions.add("Dropped " + dropped);
                    }
                }

                // _____LEVEL 4: Scoring_____
                for (int i = 0; i < players.size(); i++) {

                    Player p = players.get(i);
                    List<Card> finalHand = hands.get(i);
                    String bonusSuit = bonusSuits.get(i);

                    Map<String, Integer> totals =
                            HandUtils.suitTotals(finalHand);

                    String bestSuit = HandUtils.bestSuit(totals);
                    int score = HandUtils.bestSuitTotal(totals);

                    if (bestSuit.equalsIgnoreCase(bonusSuit)) {
                        score += 5;
                        System.out.println("\nBONUS ACTIVATED for " + p.getName() + "!");
                    }

                    p.addToTotal(score);

                    replay.add(new ReplayGame(
                            round,
                            p.getName(),
                            initialHands.get(i),
                            bonusSuit,
                            actions.get(i),
                            new ArrayList<>(finalHand),
                            score
                    ));

                    System.out.println(p.getName() +
                            " scored " + score + " points this round.");
                }
            }

            // _____FINAL SCORES_____
            System.out.println("\n-------- FINAL SCORES (DESC) --------");

            players.sort((a, b) ->
                    Integer.compare(b.getTotalScore(), a.getTotalScore()));

            for (Player p : players) {
                System.out.println(p.getName() + ": " + p.getTotalScore());
            }

            // _____LEVEL 6: High scores_____
            for (Player p : players) {
                int avg = p.getTotalScore() / numRounds;
                highScores.addScore(p.getName(), avg);
            }

            
            highScores.save();
            

            System.out.print("\nView High Score Table? (Yes/No): ");
            if (input.nextLine().equalsIgnoreCase("yes")) {
                System.out.println();
                highScores.print();
            }

            //LEVEL 8: Replay
            System.out.print("\nView replay of this game? (Yes/No): ");
            if (input.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("\n------ GAME REPLAY ------");
                for (ReplayGame e : replay) {
                    e.print();
                }
            }

            System.out.print("\nPlay again? (Yes/No): ");
            if (!input.nextLine().equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        input.close();
    }

    // INPUT help stuff

    private static int readIntInRange(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = Integer.parseInt(sc.nextLine());
                if (v >= min && v <= max) return v;
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid input.");
        }
    }

    private static String readSuit(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            if (s.equalsIgnoreCase("Hearts") ||
                s.equalsIgnoreCase("Diamonds") ||
                s.equalsIgnoreCase("Clubs") ||
                s.equalsIgnoreCase("Spades")) {
                return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
            }
            System.out.println("Invalid suit.");
        }
    }

    private static String readLetterAtoE(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String l = sc.nextLine().toUpperCase();
            if (l.matches("[A-E]")) return l;
            System.out.println("Invalid letter.");
        }
    }
}

