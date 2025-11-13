import java.util.Scanner;
import java.util.logging.Logger;

// added   3 methods



public class Calculator {

    private static final Logger logger = Logger.getLogger(Calculator.class.getName());

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        logger.info(() -> "Enter first number:");
        double num1 = input.nextDouble();

        logger.info(() -> "Enter second number:");
        double num2 = input.nextDouble();

        String resultDes = "Result is:  %.2f";

        final double result1 = addValues(num1, num2);
        logger.info(() -> String.format(resultDes, result1));

        final double result2 = subtractValue(num1, num2);
        logger.info(() -> String.format(resultDes, result2));

        final double result3 = multiplyValues(num1, num2);
        logger.info(() -> String.format(resultDes, result3));

        final double result4 = divideValues(num1, num2);
        logger.info(() -> String.format(resultDes, result4));

        input.close();
    }

    public static double addValues(double num1, double num2) {
        return num1 + num2;
    }


    public static double subtractValue(double num1, double num2) {
        return num1 - num2;
    }

    public static double multiplyValues(double num1, double num2) {
        return num1 * num2;
    }

    public static double divideValues(double num1, double num2) {
        return num1 / num2;
    }
}