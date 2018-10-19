package Domain;

import HasID.HasID;

public class Student implements HasID<Integer> {
    private int studentID; //Numarul matricol
    private String name;
    private int group;
    private String email;
    private String labTeacher;

    public Student(int studentID, String name, int group, String email, String labTeacher) {
        this.studentID = studentID;
        this.name = name;
        this.group = group;
        this.email = email;
        this.labTeacher = labTeacher;
    }

    @Override
    public Integer getID() {
        return studentID;
    }

    @Override
    public void setID(Integer studentID) throws IllegalArgumentException {
        if(studentID == null)
            throw new IllegalArgumentException("studentID cannot be null");
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public int getGroup() {
        return group;
    }

    public String getEmail() {
        return email;
    }

    public String getLabTeacher() {
        return labTeacher;
    }
}
