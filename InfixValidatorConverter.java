import java.util.Scanner;
import java.util.Stack;

public class InfixValidatorConverter {

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

            if (c == ' ') continue;

            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) return false;
                stack.pop();
            } else if (isOperator(c)) {
                if (prev == '\0' || isOperator(prev) || prev == '(')
                    return false;
            } else if (isOperand(c)) {
                if (prev == ')') return false;
            } else {
                return false;
            }

            prev = c;
        }

        if (isOperator(prev)) return false;
        return stack.isEmpty();
    }

    public static String infixToPostfix(String infix) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (c == ' ') continue;
            if (isOperand(c)) {
                output.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                stack.pop();
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && isOperator(stack.peek()) &&
                       precedence(stack.peek()) >= precedence(c)) {
                    output.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }

        return output.toString();
    }

    public static String infixToPrefix(String infix) {
        StringBuilder reversed = new StringBuilder();

        for (int i = infix.length() - 1; i >= 0; i--) {
            char c = infix.charAt(i);
            if (c == '(') reversed.append(')');
            else if (c == ')') reversed.append('(');
            else reversed.append(c);
        }

        String postfix = infixToPostfix(reversed.toString());
        return new StringBuilder(postfix).reverse().toString();
    }

    public static int precedence(char op) {
        switch (op) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan notasi infix: ");
        String expr = input.nextLine();

        if (isValidInfix(expr)) {
            System.out.println("\u2705 Notasi infix valid.");

            String postfix = infixToPostfix(expr);
            String prefix = infixToPrefix(expr);

            System.out.println("Postfix: " + postfix);
            System.out.println("Prefix : " + prefix);

        } else {
            System.out.println("\u274C Notasi infix tidak valid.");
        }

        input.close();
    }
}