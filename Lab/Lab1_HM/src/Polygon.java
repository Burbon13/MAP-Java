import java.util.Collections;
import java.util.Vector;
import java.util.regex.Matcher;

public class Polygon {
    private Vector<ComplexNumber> coordinates;

    public Polygon(ComplexNumber ... cnList) {
        coordinates = new Vector<>();
        Collections.addAll(coordinates, cnList);
    }

    public Vector<ComplexNumber> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Vector<ComplexNumber> coordinates) {
        this.coordinates = coordinates;
    }

    private double getDistance(int p1, int p2) {
        double x1,x2,y1,y2;
        x1 = coordinates.get(p1).getImaginary();
        x2 = coordinates.get(p2).getImaginary();
        y1 = coordinates.get(p1).getReal();
        y2 = coordinates.get(p2).getReal();
        return Math.sqrt(Math.abs(x1-x2)*Math.abs(x1-x2)+Math.abs(y1-y2)*Math.abs(y1-y2));
    }

    double perimeter() {
        double perimeterToReturn = 0;
        double x1,y1,x2,y2;
        for(int index = 0; index < coordinates.size() - 1; index ++)
            perimeterToReturn += getDistance(index,index+1);
        perimeterToReturn += getDistance(0,coordinates.size()-1);

        return perimeterToReturn;
    }

    @Override
    public String toString() {
        StringBuilder strB = new StringBuilder();
        for(ComplexNumber cn : coordinates)
            strB.append(cn);
        return strB.toString();
    }
}
