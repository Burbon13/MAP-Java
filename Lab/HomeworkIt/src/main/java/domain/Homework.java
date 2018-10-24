package domain;

import hasID.HasID;

import java.io.Serializable;

/**
 * Domain class for homework
 */
public class Homework implements HasID<Integer>, Serializable {
    private int number; //unique id
    private String description;
    private int given; //week
    private int deadline; //week

    /**
     * Constructor
     * @param number the problems unique id
     * @param description the problem's short description
     * @param given the problem's starting week
     * @param deadline the problem's deadline week
     */
    public Homework(int number, String description, int given, int deadline) {
        this.number = number;
        this.description = description;
        this.given = given;
        this.deadline = deadline;
    }

    /**
     * Getter for the uniqueID
     * @return the unique id
     */
    @Override
    public Integer getID() {
        return number;
    }

    /**
     * Setter for the uniqueID
     * @param number the unique id
     */
    @Override
    public void setID(Integer number) {
        this.number = number;
    }

    /**
     * checks if an object is equal to the homework
     * @param obj instance of the Object class
     * @return true if obj is a Homework instance and if they have the same id
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof Homework))
            return false;

        return this.number == ((Homework) obj).number;
    }

    /**
     * Getter for description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return the starting week of the homework
     */
    public int getGiven() {
        return given;
    }

    /**
     *
     * @return the deadline for the homework
     */
    public int getDeadline() {
        return deadline;
    }
}
