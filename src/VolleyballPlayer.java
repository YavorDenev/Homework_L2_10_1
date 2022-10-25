public class VolleyballPlayer implements Trainable {
    private String name;
    private int age;
    private int skills;
    private int condition;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSkills() {
        return skills;
    }

    public void setSkills(int skills) throws Exception {
        if (skills < 1 || skills > 10) {
            throw new Exception("The skills are not between 1 and 10 !");
        } else this.skills = skills;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) throws Exception {
        if (condition < 1 || condition > 5) {
            throw new Exception("The condition is not between 1 and 5 !");
        } else this.condition = condition;
    }

    public VolleyballPlayer(String name, int age, int skills, int condition) throws Exception {
        this.name = name;
        this.age = age;
        setSkills(skills);
        setCondition(condition);
    }

    @Override
    public void train() {
        if (this.condition > 1) {
            this.condition--;
            if (this.skills < 10) this.skills++;
        } else System.out.println("The player " + this.name + " is out of condition and unable to train.");
    }

    @Override
    public void rest() {
        if (this.condition < 5) this.condition++;
    }

    @Override
    public String toString() {
        return " player {" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", skills=" + skills +
                ", condition=" + condition +
                '}';
    }
}
