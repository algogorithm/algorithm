import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888_연산자_끼워넣기 {
    static enum Operator {
        PLUS(0),
        MINUS(1),
        MULTIPLY(2),
        DIVISION(3)
        ;

        private int value;
        Operator(int value) {
            this.value = value;
        }
    }
    static int[] nums;
    static int minAns = Integer.MAX_VALUE;
    static int maxAns = Integer.MIN_VALUE;
    static int[] operator = new int[4];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        operator[Operator.PLUS.value] = Integer.parseInt(st.nextToken());
        operator[Operator.MINUS.value] = Integer.parseInt(st.nextToken());
        operator[Operator.MULTIPLY.value] = Integer.parseInt(st.nextToken());
        operator[Operator.DIVISION.value] = Integer.parseInt(st.nextToken());

        cal(1, nums[0]);
        System.out.println(maxAns);
        System.out.println(minAns);
    }

    static void cal(int idx, int res) {
        if(idx >= nums.length) {
            minAns = Math.min(minAns, res);
            maxAns = Math.max(maxAns, res);
            return;
        }

        for(Operator op : Operator.values()) {
            if(operator[op.value] <= 0) continue;
            --operator[op.value];
            switch (op) {
                // +
                case PLUS:
                    cal(idx+1, res + nums[idx]);
                    break;
                // -
                case MINUS:
                    cal(idx+1, res - nums[idx]);
                    break;
                // *
                case MULTIPLY:
                    cal(idx+1, res * nums[idx]);
                    break;
                // /
                case DIVISION:
                    cal(idx+1, res / nums[idx]);
                    break;
                default:
                    System.out.println("Something Wrong!");
                    break;
            }
            ++operator[op.value];
        }
    }
}
