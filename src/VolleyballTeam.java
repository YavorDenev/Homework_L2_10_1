import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class VolleyballTeam implements TeamTrainable{
    private String name;
    private String stadium;
    private String coach;
    private String playersFile;
    private double strength = 0;
    private ArrayList<VolleyballPlayer> players = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getStadium() {
        return stadium;
    }

    public String getCoach() {
        return coach;
    }

    public ArrayList<VolleyballPlayer> getPlayers() {
        return players;
    }

    public double getStrength() {
        return strength;
    }

    public VolleyballTeam(String name, String stadium, String coach, String playersFile) {
        this.name = name;
        this.stadium = stadium;
        this.coach = coach;
        this.playersFile = playersFile;
    }

    public void readPlayers() {
        try {
            File file = new File(this.playersFile);
            Scanner sc = new Scanner(file, "windows-1251");
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                VolleyballPlayer player = new VolleyballPlayer(sc.next().replaceAll("[\n \r]", ""),
                        sc.nextInt(), sc.nextInt(), sc.nextInt());
                players.add(player);
            }
            sc.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void writePlayers() {
        try {
            PrintStream pr = new PrintStream(this.playersFile);
            boolean f = false;
            for (VolleyballPlayer pl: players) {
                if (f) pr.println();
                f = true;
                pr.print(pl.getName() + ";" + pl.getAge() + ";" + pl.getSkills() + ";" + pl.getCondition() + ";");
            }
            pr.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void showPlayers() {
        if (this.players.isEmpty()) readPlayers();
        int i = 1;
        System.out.println(this.name + " players:");
        for (VolleyballPlayer player : players) System.out.println((i++) + ". " + player);
    }

    public void addPlayer(String name, int age, int skills, int condition){
        try {
            this.players.add(new VolleyballPlayer(name, age, skills, condition));
            calculateStrength();
            writePlayers();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void removePlayer(int num){
        try {
            if (this.players.size() > 6) {
                this.players.remove(num - 1);
                calculateStrength();
                writePlayers();
            } else System.out.println("The player cannot be removed because there must be at least 6 players.");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void calculateStrength() {
        if (this.players.isEmpty()) readPlayers();
        this.strength = 0;
        for (VolleyballPlayer player: players) this.strength += player.getSkills();
        this.strength /= players.size();
    }

    @Override
    public void teamTrain() {
        for (VolleyballPlayer player: players) player.train();
        calculateStrength();
        writePlayers();
    }

    @Override
    public void teamRest() {
        for (VolleyballPlayer player: players) player.rest();
        writePlayers();
    }

    @Override
    public String toString() {
        return "team {" +
                "name='" + name + '\'' +
                ", stadium='" + stadium + '\'' +
                ", coach='" + coach + '\'' +
                ", strength=" + strength +
                '}';
    }
}
