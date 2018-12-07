package I;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentMap sm = new StudentMap();
        sm.addStudent(new Student(7.8, "Razvan"));

        List<Student> l = new ArrayList<>();
        l.add(new Student(7.51, "Geroge"));
        l.add(new Student(8.08, "Michale"));
        l.add(new Student(5.00, "Daniel"));

        sm.put(8,l); //inconsistenta + suprascriere
        sm.addStudent(new Student(4.00, "Vlad"));
        sm.addStudent(new Student(4.20, "Florin"));
        sm.addStudent(new Student(5.10, "Ciobanu"));
        sm.addStudent(new Student(4.90, "Hehe"));

        System.out.println(sm.size());
        for(List<Student> dl : sm.values()) {
            dl.sort(Student::compareTo);
            for(Student st: dl)
                System.out.println(st);
        }

        System.out.println("--");

        for(Integer key: sm.keySet()) {
            Collections.sort(sm.get(key));
            for(Student st: sm.get(key))
                System.out.println(st);
        }

        System.out.println("--comparator local--");

        for(Integer key: sm.keySet()) {
            sm.get(key).sort(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Double.compare(o2.getGrade(), o1.getGrade());
                }
            });
            for(Student st: sm.get(key))
                System.out.println(st);
        }
    }
}
