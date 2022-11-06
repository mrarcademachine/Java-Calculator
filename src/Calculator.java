import java.io.IOException;
import java.util.Scanner;
import java.lang.String;

public class Calculator {
    public static void main(String[] args) throws IOException{
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter a mathematical expression: ");
        String inputString = userInput.nextLine();

        String outputString = Main.calc(inputString);
        System.out.println(outputString);
    }
}
class Main {
    public static String calc(String input) throws IOException{

        String[] expressionParts = input.split(" ");

        if (expressionParts.length != 3) {
            throw new IOException("Invalid number of operands and/or operators");
        }

        String strNum1 = expressionParts[0];
        String strNum2 = expressionParts[2];
        String operator = expressionParts[1];
        int num1;
        int num2;

        boolean isArabic = strNum1.matches("[1-9]|10") && strNum2.matches("[1-9]|10");
        boolean isRoman = strNum1.matches("[IVX]+") && strNum2.matches("[IVX]+");
        if (isArabic) {
            num1 = Integer.parseInt(strNum1);
            num2 = Integer.parseInt(strNum2);
        }
        else if (isRoman) {
            Roman romanObj1 = Roman.valueOf(strNum1);
            Roman romanObj2 = Roman.valueOf(strNum2);

            num1 = romanObj1.getArabic();
            num2 = romanObj2.getArabic();
        } else {
            throw new IOException("Not a number");
        }

        if (num1 < 1 || num1 > 10 || num2 <1 || num2 > 10) {
            throw new IOException("Number not supported");
        }

        int result = switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new UnsupportedOperationException("Incorrect operator");
        };

        if (isArabic) {
            return Integer.toString(result);
        } else
            if (result <= 0)
                throw new ArithmeticException("Result in Roman cannot be zero or negative");
            /*  Результат от 11 до 99, кроме чистых десятков, разбивается на десятки и единицы,
                затем их значение подставляется из enum и складывается в строку */
            if (result > 10 && result < 100 && result % 10 != 0) {
                String resultTens = Roman.arabicToRoman(result / 10 * 10);
                String resultOnes = Roman.arabicToRoman(result % 10);
                return resultTens + resultOnes;
            }

            return Roman.arabicToRoman(result);



            }
    }
