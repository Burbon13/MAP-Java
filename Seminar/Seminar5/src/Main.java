import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Brute choice
        //Genereaza fisier Main$1.class compilat
        Area<Circle> ac = new Area<Circle>() {
            @Override
            public double computeArea(Circle circle) {
                return Math.PI * Math.pow(circle.getRadius(), 2);
            }
        };

        Circle c = new Circle(2);
        System.out.println(ac.printClass(c) + " " + ac.computeArea(c));

        //Lamba
        Area<Circle> ac2 = (Circle circle) -> Math.PI * Math.pow(circle.getRadius(), 2);
        System.out.println(ac2.printClass(c) + " " + ac2.computeArea(c));

        Area<Square> as = square -> Math.pow(square.getSide(), 2);
        Square s = new Square(3);
        System.out.println(as.printClass(s) + " " + as.computeArea(s));


        ArrayList<Square> slist = new ArrayList<>();
        slist.add(new Square(3));
        slist.add(new Square(1));
        slist.add(new Square(5));
        slist.add(new Square(6));
        slist.add(new Square(3));
        printArea(slist, as);
    }

    public static <E> void printArea(List<E> l, Area<E> a) {
        l.forEach(ele -> System.out.println(a.getClass().toString() + " " + a.computeArea(ele)));
    }
}
