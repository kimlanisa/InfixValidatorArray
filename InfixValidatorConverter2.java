import java.util.Scanner;
import java.util.Stack;

public class InfixValidatorConverter2 {

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

    public static int precedence(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
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
                if (!stack.isEmpty()) stack.pop(); // pop '('
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

    public static int evaluatePostfix(String expr) {
        Stack<Integer> stack = new Stack<>();

        for (char c : expr.toCharArray()) {
            if (c == ' ') continue;

            if (Character.isDigit(c)) {
                stack.push(c - '0'); // char ke int
            } else if (isOperator(c)) {
                int b = stack.pop();
                int a = stack.pop();
                int result = applyOperator(a, b, c);
                stack.push(result);
            }
        }

        return stack.pop();
    }

    public static int evaluatePrefix(String expr) {
        Stack<Integer> stack = new Stack<>();

        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == ' ') continue;

            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else if (isOperator(c)) {
                int a = stack.pop();
                int b = stack.pop();
                int result = applyOperator(a, b, c);
                stack.push(result);
            }
        }

        return stack.pop();
    }

    private static int applyOperator(int a, int b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            case '^' -> (int) Math.pow(a, b);
            default -> 0;
        };
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan notasi infix: ");
        String expr = input.nextLine();

        if (isValidInfix(expr)) {
            System.out.println("✅ Notasi infix valid.");

            String postfix = infixToPostfix(expr);
            String prefix = infixToPrefix(expr);

            System.out.println("Postfix: " + postfix);
            System.out.println("Prefix : " + prefix);

            // Evaluasi jika semua karakter adalah angka dan operator
            if (expr.matches("[\\d+\\-*/^() ]+")) {
                int hasilPostfix = evaluatePostfix(postfix);
                int hasilPrefix = evaluatePrefix(prefix);

                System.out.println("Hasil evaluasi Postfix: " + hasilPostfix);
                System.out.println("Hasil evaluasi Prefix : " + hasilPrefix);
            } else {
                System.out.println("ℹ️ Evaluasi hanya berlaku untuk angka 0–9.");
            }
        } else {
            System.out.println("❌ Notasi infix tidak valid.");
        }

        input.close();
    }
}