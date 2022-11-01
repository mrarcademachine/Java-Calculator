import java.util.Scanner;
import java.lang.String;

public class Calc {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter a mathematical expression: ");
        String[] expressionStrings = userInput.nextLine().split(" ");

        if (expressionStrings.length != 3) {
            throw new ArrayIndexOutOfBoundsException("Invalid number of operands and/or operators");
        };

        int operand1 = Integer.parseInt(expressionStrings[0]);
        int operand2 = Integer.parseInt(expressionStrings[2]);
        int result;
        String operator = expressionStrings[1];

        result = switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> operand1 / operand2;
            default -> throw new UnsupportedOperationException();
        };
        System.out.println(result);


    }
}