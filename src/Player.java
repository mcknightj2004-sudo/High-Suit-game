public class Player {       //identity and running total across da rounds
    private final String name;
    private int totalScore = 0;


    public Player(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public boolean isComputer() {
    //__Level 7__: If name is Computer then decisions are automatic
        return name.equalsIgnoreCase("Computer");
    }


    public void addToTotal(int score) {     //keeps it in the one place
        totalScore += score;
    }


    public int getTotalScore() {
        return totalScore;
    }
}
