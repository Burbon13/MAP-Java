import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class StudentMap extends TreeMap<Integer, List<Student>> {
    public StudentMap() {
        super();
    }
    public void add(Student student)
    {
        int medie = Math.round(student.getMedie());
        List<Student> l = this.get(medie);

        if(l == null) {
            l = new ArrayList<Student>();
            this.put(medie, l);
        }
        l.add(student);
    }
}
