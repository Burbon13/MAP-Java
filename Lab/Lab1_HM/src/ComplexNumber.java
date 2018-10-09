import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexNumber {
    private double real = 0, imaginary = 0;

    public ComplexNumber () {}

    public ComplexNumber(String cNumber) {
        this.imaginary = ComplexNumber.getImaginaryFromString(cNumber);
        this.real = ComplexNumber.getRealFromString(cNumber);
    }

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public static boolean isValidComplexNumber(String cNumber) {
        if(cNumber.length() == 0)
            return false;

        Pattern pattern = Pattern.compile("([-+]?[0-9]+(\\.[0-9]+)?)?([-+]?([0-9]+(\\.[0-9]+)?)?i)?");
        Matcher matcher = pattern.matcher(cNumber);
        return matcher.matches();
    }

    public static double getImaginaryFromString(String cNumber) {
        Pattern pattern = Pattern.compile("[-+]?[0-9]+(\\.[0-9]+)?i");
        Matcher matcher = pattern.matcher(cNumber);

        if(matcher.find()) {
            String nrS = matcher.group();
            nrS = nrS.substring(0, nrS.length()-1);
            return Double.parseDouble(nrS);
        }
        else if(cNumber.charAt(cNumber.length()-1) == 'i') {
            if(cNumber.length()-2 >= 0 && cNumber.charAt(cNumber.length()-2) == '-')
                return -1;
            else
                return 1;
        }
        return 0;
    }

    public static double getRealFromString(String cNumber) {
        Pattern pattern = Pattern.compile("[-+]?[0-9]+(\\.[0-9]+)?([+-]|$)");
        Matcher matcher = pattern.matcher(cNumber);

        if (matcher.find()) {
            String nrS = matcher.group();
            if(!Character.isDigit(nrS.charAt(nrS.length()-1)))
                nrS = nrS.substring(0, nrS.length() - 1);
            return Double.parseDouble(nrS);
        }
        return 0;
    }

    @Override
    public String toString() {
        return real + (imaginary >= 0 ? "+" : "") + imaginary + "i\n";
    }

    public void add(ComplexNumber cn) {
        this.imaginary += cn.getImaginary();
        this.real += cn.getReal();
    }

    public void subtract(ComplexNumber cn) {
        this.imaginary -= cn.getImaginary();
        this.real -= cn.getReal();
    }

    public void multiply(ComplexNumber cn) {
        double auxReal = this.real * cn.getReal() - this.imaginary * cn.getImaginary();
        double auxImaginary = this.real * cn.getImaginary() + this.imaginary * cn.getReal();
        this.real = auxReal;
        this.imaginary = auxImaginary;
    }

    public void divide(ComplexNumber cn) {
        cn.setImaginary((-1)*cn.getImaginary());
        this.multiply(cn);
        double toDivide = (cn.getReal()*cn.getReal()) + (cn.getImaginary()*cn.getImaginary());
        this.real /= toDivide;
        this.imaginary /= toDivide;
    }
}
