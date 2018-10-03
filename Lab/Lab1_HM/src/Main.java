import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        //test();
        verifyExpression(args);
        createPolygon(args);
    }

    public static void createPolygon(String[] args) {
        System.out.println("Creating that ... polygon!");
        ComplexNumber[] cnArray = new ComplexNumber[args.length/2 + 1];
        for(int index = 0; index < args.length; index += 2)
            cnArray[index/2] = new ComplexNumber(args[index]);

        Polygon myPolygon = new Polygon(cnArray);
        System.out.println(myPolygon);
        System.out.println("Perimeter: " + myPolygon.perimeter());

    }

    public static void verifyExpression(String[] args) {
        System.out.println("Verifying the given expression:");
        ComplexNumber result = new ComplexNumber();
        int sign = 1;
        boolean isValid = true;
        for(int index = 0; index < args.length; index++) {
            if(index % 2 == 0) {
                if(!ComplexNumber.isValidComplexNumber(args[index])) {
                    System.out.println("Invalid expression!");
                    return;
                }
                ComplexNumber cnAux = new ComplexNumber(args[index]);
                System.out.print(result + " + " + cnAux + " = ");
                result.setImaginary(result.getImaginary()+sign*cnAux.getImaginary());
                result.setReal(result.getReal()+sign*cnAux.getReal());
                System.out.println(result);
            } else if(args[index].charAt(0) == '-')
                sign = -1;
            else
                sign = 1;
        }

        System.out.println("Result is: " + result);
    }

    public static void test() {
        ComplexNumber c1 = new ComplexNumber("1");
        ComplexNumber c2 = new ComplexNumber("2.2");
        ComplexNumber c3 = new ComplexNumber("-3");
        ComplexNumber c4 = new ComplexNumber("-4.4");
        ComplexNumber c5 = new ComplexNumber("-5.5i");
        ComplexNumber c6 = new ComplexNumber("6.6i");
        ComplexNumber c7 = new ComplexNumber("-7i");
        ComplexNumber c8 = new ComplexNumber("8i");
        ComplexNumber c9 = new ComplexNumber("-i");
        ComplexNumber c17 = new ComplexNumber("i");
        ComplexNumber c10 = new ComplexNumber("1+i");
        ComplexNumber c11 = new ComplexNumber("2-4.5i");
        ComplexNumber c12 = new ComplexNumber("-3.4+4.543i");
        ComplexNumber c13 = new ComplexNumber("-2+i");
        ComplexNumber c14 = new ComplexNumber("-5.3543-i");
        ComplexNumber c15 = new ComplexNumber("4+5.4i");
        ComplexNumber c16 = new ComplexNumber("2-5.345i");


        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);
        System.out.println(c17);
        System.out.println(c10);
        System.out.println(c11);
        System.out.println(c12);
        System.out.println(c13);
        System.out.println(c14);
        System.out.println(c15);
        System.out.println(c16);
    }
}
