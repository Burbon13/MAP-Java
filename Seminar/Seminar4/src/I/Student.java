package I;

public class Student implements Comparable<Student>{
    private double grade;
    private String name;

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name + ' ' + this.grade;
    }

    public Student(double grade, String name) {
        this.grade = grade;
        this.name = name;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }
}
