import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2631_줄세우기 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] children = new int[N];
        int[] L = new int[N];

        for(int i = 0; i < N; ++i) {
            children[i] = Integer.parseInt(br.readLine());
        }

        L[0] = children[0];
        int idx = 1;

        for(int i = 1; i < N; ++i) {
            if(L[idx - 1] < children[i]) L[idx++] = children[i];
            else if(L[0] > children[i]) L[0] = children[i];
            else {
                int insertPoint = Arrays.binarySearch(L, 0, idx, children[i]);
                L[insertPoint < 0 ? -insertPoint - 1: insertPoint] = children[i];
            }
        }

        System.out.println(N - idx);
    }
}
