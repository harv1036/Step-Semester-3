import java.util.ArrayDeque;
import java.util.Deque;

class PocketCalculator {

    static int precedence(char op) {
        if (op == '*' || op == '/') {
            return 2;
        }
        if (op == '+' || op == '-') {
            return 1;
        }
        return 0;
    }

    static String toPostfix(String infix) {
        StringBuilder output = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : infix.toCharArray()) {
            if (Character.isDigit(c)) {
                output.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && stack.peek() != '(' && precedence(stack.peek()) >= precedence(c)) {
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

    static int evaluate(String postfix) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (char c : postfix.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int right = stack.pop();
                int left = stack.pop();
                int result;
                switch (c) {
                    case '+':
                        result = left + right;
                        break;
                    case '-':
                        result = left - right;
                        break;
                    case '*':
                        result = left * right;
                        break;
                    case '/':
                        if (right == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        result = left / right;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operator: " + c);
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String[] expressions = {"2+3*4", "(2+3)*4", "8/2-3"};

        for (String expr : expressions) {
            String postfix = toPostfix(expr);
            int value = evaluate(postfix);
            System.out.println(postfix + " = " + value);
        }

        System.out.println();
        System.out.println("Time complexity: O(n) for both toPostfix and evaluate, each character is pushed and popped at most once");
        System.out.println("Extra space: O(n) for the operator stack during conversion and the operand stack during evaluation");
    }
}
