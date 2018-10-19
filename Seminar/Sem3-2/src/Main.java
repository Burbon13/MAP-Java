import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        HashSet<Student> hashSet=new HashSet<>();
        hashSet.add(new Student("Dan",4.5f));
        hashSet.add(new Student("Ana",8.5f));
        hashSet.add(new Student("Dan",4.5f));//nu se adauga
        hashSet.forEach(System.out::println);



    }
}
