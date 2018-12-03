package domain;

import hasID.HasID;

import java.io.Serializable;

/**
 * Domain class for student
 */
public class Student implements HasID<Integer>, Serializable {
    private int studentID; //Numarul matricol
    private String name;
    private int group;
    private String email;
    private String labTeacher;

    /**
     * Constructor
     * @param studentID the student's unique id
     * @param name the student's name
     * @param group the student's faculty group
     * @param email the student's email
     * @param labTeacher the student's laboratory teacher name
     */
    public Student(int studentID, String name, int group, String email, String labTeacher) {
        this.studentID = studentID;
        this.name = name;
        this.group = group;
        this.email = email;
        this.labTeacher = labTeacher;
    }

    /**
     * Getter for the uniqueID
     * @return the unique id
     */
    @Override
    public Integer getID() {
        return studentID;
    }

    /**
     * Setter for the uniqueID
     * @param studentID the unique id
     */
    @Override
    public void setID(Integer studentID) {
        this.studentID = studentID;
    }

    /**
     *
     * @param obj instance of Object
     * @return true is obj is a Student instance and if the obj studentID equals to the current studentID
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof Student))
            return false;

        return this.studentID == ((Student) obj).studentID;
    }

    /**
     * Getter for the student's name
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for faculty group
     * @return the faculty group
     */
    public int getGroup() {
        return group;
    }

    /**
     * Getter for student's email
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for the student's lab teacher name
     * @return the teacher's name
     */
    public String getLabTeacher() {
        return labTeacher;
    }

    @Override
    public String toString() {
        return "ID: " + studentID + " | Name: " + name + " | Group: " + group + " | Email: " + email + " | Teacher: " + labTeacher;
    }
}
