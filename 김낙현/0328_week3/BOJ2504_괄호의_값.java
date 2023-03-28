import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ2504_괄호의_값 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        Stack<Character> parenthesis = new Stack<>();
        int answer = 0;
        int multiplier = 1;
        for (int i = 0; i < input.length(); ++i) {
            char curChar = input.charAt(i);
            if (curChar == '(') {
                parenthesis.push(curChar);
                multiplier *= 2;
            } else if (curChar == '[') {
                parenthesis.push(curChar);
                multiplier *= 3;
            } else if (curChar == ')') {
                if (parenthesis.isEmpty() || parenthesis.pop() != '(') {
                    answer = 0;
                    break;
                } else if (input.charAt(i - 1) == '(') {
                    answer += multiplier;
                }
                multiplier /= 2;
            } else if (curChar == ']') {
                if (parenthesis.isEmpty() || parenthesis.pop() != '[') {
                    answer = 0;
                    break;
                } else if (input.charAt(i - 1) == '[') {
                    answer += multiplier;
                }
                multiplier /= 3;
            } else {
                System.out.println("Something Wrong!!");
                break;
            }
        }
        if (!parenthesis.isEmpty()) {
            answer = 0;
        }
        System.out.println(answer);
    }
}
