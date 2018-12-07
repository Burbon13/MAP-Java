package I;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class StudentMap extends TreeMap<Integer, List<Student>> {
    public StudentMap() {
        //super();
        super(new DescOrder());
    }

    public void addStudent(Student student) {
        int roundedGrade = (int)Math.round(student.getGrade());
        if(super.get(roundedGrade) == null) { //super.entries.get(roundedGrade) ???
            List<Student> l = new ArrayList<>();
            l.add(student);
            super.put(roundedGrade,l);
        } else {
            List<Student> l = super.get(roundedGrade);
            l.add(student);
        }
    }

    @Override
    public List<Student> put(Integer key, List<Student> value) {
        for(Student s: value) {
            if((int)Math.round(s.getGrade()) == key)
                addStudent(s);
        }
        return super.get(key); ///
    }

    public static class DescOrder implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
}
