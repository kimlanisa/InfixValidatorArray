import java.util.Scanner;
import java.util.Stack;

public class InfixValidatorArray {

    public static boolean isOperator(char c) {
        return "+-*/^".indexOf(c) != -1;
    }

    public static boolean isOperand(char c) {
        return Character.isLetterOrDigit(c);
    }

    public static boolean isValidInfix(String expr) {
        Stack<Character> stack = new Stack<>();
        char prev = '\0';

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == ' ')
                continue;

            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty())
                    return false;
                stack.pop();
            } else if (isOperator(c)) {
                if (prev == '\0' || isOperator(prev) || prev == '(')
                    return false;
            } else if (isOperand(c)) {
                if (prev == ')')
                    return false;
            } else {
                return false;
            }

            prev = c;
        }

        if (isOperator(prev))
            return false;
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan notasi infix: ");
        String expr = input.nextLine();

        if (isValidInfix(expr)) {
            System.out.println("Notasi infix valid.");
        } else {
            System.out.println("Notasi infix tidak valid.");
        }
    }
}