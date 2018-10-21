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
    public void setID(Integer studentID) {
        this.studentID = studentID;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof Student))
            return false;

        return this.studentID == ((Student) obj).studentID;
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
