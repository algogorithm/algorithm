import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ7490_0_만들기 {
    static StringBuilder answer = new StringBuilder();
    enum Operator {
        BLANK,
        PLUS,
        MINUS,
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; ++tc) {
            int n = Integer.parseInt(br.readLine());

            simulation(new StringBuilder("1"), 2, n);

            answer.append("\n");
        }
        System.out.println(answer);
    }

    public static int calculate(String equation) {
        int sum = 0;
        StringBuilder operand = new StringBuilder();
        for(int i = 0; i < equation.length(); ++i) {
            char element = equation.charAt(i);
            switch (element) {
                case ' ':
                    continue;
                case '+':
                    sum += Integer.parseInt(operand.toString());
                    operand = new StringBuilder("+");
                    break;
                case '-':
                    sum += Integer.parseInt(operand.toString());
                    operand = new StringBuilder("-");
                    break;
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    operand.append(element);
                    break;
                default:
                    System.out.println("Something Wrong!");
                    break;
            }
        }
        sum += Integer.parseInt(operand.toString());
        return sum;
    }
    public static boolean isZero(String equation) {
        return calculate(equation) == 0;
    }
    public static void simulation(StringBuilder equation, int i, int n) {
        if(i > n) {
            if(isZero(equation.toString())) {
                answer.append(equation).append("\n");
            }
            return;
        }

        for(Operator op : Operator.values()) {
            switch (op) {
                case PLUS:
                    simulation(equation.append("+").append(i), i+1, n);
                    break;
                case MINUS:
                    simulation(equation.append("-").append(i),i+1, n);
                    break;
                case BLANK:
                    simulation(equation.append(" ").append(i),i+1, n);
                    break;
                default:
                    System.out.println("Something Wrong!");
            }
            equation.setLength(equation.length()-2);
        }
    }
}
