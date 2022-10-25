import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**     Ако искате да рестартирате приложението с първоначалните данни, то в папката
        "here-are-the-start-csv-files" се пази началното съдържание на файловете pl-1, pl-2 и pl-3      */

public class Main {
    public static ArrayList<VolleyballTeam> teams = new ArrayList<>();

    public static void readTeams() {
        try {
            File file = new File("teams.csv");
            Scanner sc = new Scanner(file, "windows-1251");
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                VolleyballTeam team = new VolleyballTeam(sc.next().replaceAll("[\n \r]", ""),
                        sc.next(), sc.next(), sc.next());
                team.readPlayers();
                team.calculateStrength();
                teams.add(team);
            }
            sc.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static void showTeams() {
        if (teams.isEmpty()) readTeams();
        int i = 1;
        for (VolleyballTeam team : teams) {
            System.out.println((i++) + ". " + team);
        }
    }

    public static void main(String[] args) {
        showTeams();

        System.out.println();
        for (VolleyballTeam team: teams) team.showPlayers();

        System.out.println();
        teams.get(1).removePlayer(2);
        teams.get(1).showPlayers();
        System.out.println("Strength - " + teams.get(1).getStrength());

        System.out.println();
        teams.get(2).teamTraining();
        teams.get(2).teamRest();
        teams.get(2).teamTraining();
        teams.get(2).addPlayer("Xxxx", 99, 9, 4);
        teams.get(2).showPlayers();
        System.out.println("Strength - " + teams.get(2).getStrength());

        System.out.println();
        Match.play(teams.get(0), teams.get(1));
        Match.play(teams.get(0), teams.get(2));
        Match.play(teams.get(1), teams.get(2));

        System.out.println();
        Match.showResults();
    }
}