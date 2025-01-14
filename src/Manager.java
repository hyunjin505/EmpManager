public class Manager extends Employee {

    private int teamSize;

    public Manager(String name, int id, int salary, int teamSize) {
        super(name, id, salary);
        this.teamSize = teamSize;
    }

    @Override
    public void introduce() {
        super.introduce();
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "teamSize=" + teamSize +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", salary=" + salary +
                '}';
    }
}
