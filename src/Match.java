import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Match {

    private static Random rnd = new Random();

    private static List<String> results = new ArrayList<>();

    private static final String RESULT_FILE = "results.txt";

    public static void play(VolleyballTeam team1, VolleyballTeam team2) {
        String result = "";
        double diff = generateMatchStrength(team1) - generateMatchStrength(team2);
        if (diff > 50) result = "3:0";
        else if (diff > 20) result = "3:1";
        else if (diff > 0 ) result = "3:2";
        else if (diff > -20) result = "2:3";
        else if (diff > -50) result = "1:3";
        else result = "0:3";
        System.out.println("Teams " + team1.getName() + " and " + team2.getName() + " finished at " + result);
        result = team1.getName() + " vs " + team2.getName() + "  ---  " + result;
        if (results.isEmpty()) readResults();
        results.add(result);
        writeResults();
    }

    private static double generateMatchStrength(VolleyballTeam team) {
        return 10 * team.getStrength() + rnd.nextDouble(24) + 1;
    }

    public static void showResults() {
        if (results.isEmpty()) readResults();
        for (String result : results) System.out.println(result);
    }

    private static void readResults() {
        try {
            File file = new File(RESULT_FILE);
            Scanner sc = new Scanner(file, "windows-1251");
            while (sc.hasNextLine()) {
                String result = sc.nextLine();
                results.add(result);
            }
            sc.close();
        } catch (FileNotFoundException f){
            results.add("-------- RESULTS --------");
        } catch (Exception е){
            System.out.println(е);
        }
    }

    private static void writeResults() {
        try {
            PrintStream pr = new PrintStream(RESULT_FILE);
            for (String result: results) { pr.println(result); }
            pr.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
