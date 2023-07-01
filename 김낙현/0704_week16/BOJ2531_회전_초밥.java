import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ2531_회전_초밥 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N];

        for (int i = 0; i < N; ++i) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int[] inStomach = new int[d + 1];

        int left = 0;
        int right = k - 1;
        int max = 0;
        int sushiCnt = 0;

        for (int i = left; i <= right; ++i) {
            if(inStomach[sushi[i]] == 0) ++sushiCnt;
            ++inStomach[sushi[i]];
        }

        max = (inStomach[c] == 0) ? sushiCnt + 1: sushiCnt;
        for (; left < N - 1; ++left) {
            right = (right + 1) % N;
            // 먹고
            ++inStomach[sushi[right]];
            if(inStomach[sushi[right]] == 1) ++sushiCnt;

            // 뱉어
            --inStomach[sushi[left]];
            if (inStomach[sushi[left]] == 0) --sushiCnt;

            max = Math.max(max, (inStomach[c] == 0) ? sushiCnt + 1 : sushiCnt);
        }

        System.out.println(max);
    }
}