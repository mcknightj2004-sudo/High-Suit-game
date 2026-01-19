import java.io.*;
import java.util.*;


public class HighScoreTable {


    //_____LEVEL 6 HIGH SCORE TABLE_____ //
    //store the top 5 scores 
    private final List<HighScoreEntry> entries = new ArrayList<>();
    private final String fileName;


    public HighScoreTable(String fileName) {
        this.fileName = fileName;
    }


    public void load() {        //warn if corrupted or gone
        entries.clear();


        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;


                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    entries.add(new HighScoreEntry(name, score));
                }
            }
        } catch (FileNotFoundException e) {
            // No file yet - that's alright
        } catch (Exception e) {
            System.out.println("Warning: Could not load high scores (file may be corrupted).");
        }

        sortAndTrimTop5();  //keep top 5
    }


    public void addScore(String name, int avgScore) {
        entries.add(new HighScoreEntry(name, avgScore));
        sortAndTrimTop5();
    }


    private void sortAndTrimTop5() {
        entries.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        while (entries.size() > 5) {
            entries.remove(entries.size() - 1);
        }
    }

//writes them to file
    public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (HighScoreEntry e : entries) {
                writer.println(e.getName() + "," + e.getValue());
            }

        } catch (IOException e) {
            System.out.println("Warning: Could not save high scores to file.");
        }
        
                
    }

// just the format
    public void print() {
    System.out.println("\n------HIGHSCORE TABLE (Avg. Score) ------");
    
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i));
        }
    }


    public boolean isEmpty() {
        return entries.isEmpty();
    }
}
