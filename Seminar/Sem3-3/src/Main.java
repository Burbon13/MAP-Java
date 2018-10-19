import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        StudentMap sm = new StudentMap();
        sm.add(new Student("Ion", 5.75f));
        sm.add(new Student("Ion", 5.80f));
        sm.add(new Student("Ion", 5.25f));

        for(Map.Entry<Integer, List<Student>> entry:sm.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        ArrayList<Student> r = new ArrayList<Student>();

        r.add(new Student("Ion", 5.13f));
        r.add(new Student("Ion", 6.20f));
        r.add(new Student("Ion", 8.50f));

        sm.put(5, r);
        System.out.println("");
        for(Map.Entry<Integer, List<Student>> entry:sm.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
