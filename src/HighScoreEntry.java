public class HighScoreEntry {
    private final String name;
    private final int value;

//one row in the leaderboard
//and will keeps data together when sorting
    public HighScoreEntry(String name, int value) {
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }


    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        return name + ": " + value + " pts";
    }
}