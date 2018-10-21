package Domain;

import HasID.HasID;

public class Homework implements HasID<Integer> {
    private int number; //unique id
    private String description;
    private int given; //week
    private int deadline; //week

    public Homework(int number, String description, int given, int deadline) {
        this.number = number;
        this.description = description;
        this.given = given;
        this.deadline = deadline;
    }

    @Override
    public Integer getID() {
        return number;
    }

    @Override
    public void setID(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof Homework))
            return false;

        return this.number == ((Homework) obj).number;
    }

    public String getDescription() {
        return description;
    }

    public int getGiven() {
        return given;
    }

    public int getDeadline() {
        return deadline;
    }
}
