package domain;

import hasID.HasID;
//import javafx.util.Pair;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mark implements Serializable, HasID<Pair<Integer, Integer>> {
    private Pair<Integer, Integer> id;
    private Student student;
    private Homework homework;
    private double value;
    private LocalDateTime date;
    private String feedback;

    public Mark(Student student, Homework homework, double value, String feedback) {
        this.student = student;
        this.homework = homework;
        this.value = value;
        this.date = LocalDateTime.now();
        this.id = new Pair<>(student.getID(), homework.getID());
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }

    public Student getStudent() {
        return student;
    }

    public Homework getHomework() {
        return homework;
    }

    public double getValue() {
        return value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "student=" + student.getID() + " | " +
                " homework=" + homework.getID() + "|" +
                " value=" + value + " | " +
                " date=" + date.getDayOfMonth() + "." + date.getMonth() + "." + date.getYear() + " | " +
                " feedback=" + feedback +
                '}';
    }

    @Override
    public Pair<Integer, Integer> getID() {
        return id;
    }

    @Override
    public void setID(Pair<Integer, Integer> integerIntegerPair) {
        id = integerIntegerPair;
    }
}
