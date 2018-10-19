package Domain;

import HasID.HasID;

public class Homework implements HasID<Integer> {
    private int number; //unique id
    private String description;
    private int deadline; //week
    private int finished; //week
    private int mark;

    @Override
    public Integer getID() {
        return number;
    }

    @Override
    public void setID(Integer number) {
        if(number == null)
            throw new IllegalArgumentException("number cannot be null");
        this.number = number;
    }
}
