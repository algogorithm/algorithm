import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1722 {
    static int N;
    // 문제조건에서 N은 최대 20까지 가능하다.
    // 20!를 계산하게된다면 int범위를 초과하기 때문에 long으로 선언해준다.
    static long K, cntK;
    // 소문제 번호를 판단하기 위한 변수
    static int problemNo;
    // 소문제 번호가 2번일 경우, 찾아야 하는 순열을 저장하기 위한 배열.
    static int[] targetPermutation;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 두개의 소 문제 중 어떤것인지 확인하는 변수
        problemNo = Integer.parseInt(st.nextToken());
        targetPermutation = new int[N];
        if(problemNo == 1){
            // K가 int범위를 초과할 수 있으므로, long으로 받아준다.
            // 만약 문제가 아래와같이 나온다면, 범위를 초과한다.
            // 20
            // 1 2432902008176640000
            K = Long.parseLong(st.nextToken());
        }
        else if(problemNo == 2){
            int idx = 0;
            while(st.hasMoreTokens()){
                targetPermutation[idx++] = Integer.parseInt(st.nextToken());
            }
            // K와 마찬가지로 int범위를 초과할 수 있으므로, long으로 받아준다.
            cntK = 0;
        }
        // 본 코드
        dfs(0, new int[N], new boolean[N+1]);
    }
    private static boolean dfs(int depth, int[] permutation, boolean[] v){
        if(depth == N){
            // 종료조건에 따라 수행하는 로직이 다르다.
            if(problemNo == 1){
                System.out.println(Arrays.toString(permutation).replaceAll("[\\[\\],]", ""));
                return true;
            }
            else if(problemNo == 2){
                cntK++;
                System.out.println(cntK);
                return true;
            }
        }
        for(int currNum = 1; currNum <= N; currNum++){
            // 이미 방문했던 숫자라면 다음숫자로 넘어간다.
            if(v[currNum] == true){
                continue;
            }
            // problemNo에 따라 다른 로직을 적용해준다.
            // problemNo가 1일경우
            if(problemNo == 1){
                // K를 판별하기 위해선 어떻게해야할까?
                // 현재 depth에서의 순열의 개수는 factorial(N-depth-1)이다.
                // 만약 K가 현재 depth에서의 순열의 개수보다 크다면, 현재 depth에서의 순열에는 K번째 순열이 없다.
                // 따라서 K에서 현재 depth에서의 순열의 개수를 빼준다.
                long temp = factorial(N-depth-1);
                // K가 temp보다 크다면 현재 반복문 다음에 나오는 숫자로 넘어가야한다.
                if(K > temp){
                    // 넘어가기 전에 현재 수에서 만들어질 수 있는 모든 순열의 개수를 K에서 빼준다.
                    K -= temp;
                    continue;
                }
                // 만약 K가 temp보다 작거나 같다면, 현재숫자로 시작하는 순열 안아 K번째 순열이 있다.
                else if(K <= temp){
                    // 현재 수를 현재 depth위치의 permutation에 넣어준다.
                    permutation[depth] = currNum;
                    // 방문처리
                    v[currNum] = true;
                    // 다음 depth로 넘어간 후 K번째 순열을 찾았다면 true를 반환해준다.
                    if(dfs(depth+1, permutation, v) == true){
                        return true;
                    }
                }
            }
            // problemNo가 2일경우
            else if(problemNo == 2){
                // 만약 현재 숫자가 depth번째 숫자와 다르다면 cntK에 factorial(N-depth-1)을 더해준다.
                if(currNum != targetPermutation[depth]){
                    cntK += factorial(N-depth-1);
                    continue;
                }
                // 만약 현재 숫자가 depth번째 숫자와 같다면, 현재 숫자를 permutation에 넣어주고, 방문처리를 해준다.
                else if(currNum == targetPermutation[depth]){
                    permutation[depth] = currNum;
                    v[currNum] = true;
                    if(dfs(depth+1, permutation, v) == true){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // factorial 함수
    private static long factorial(int n){
        if(n <= 1) return 1;
        return n * factorial(n-1);
    }
}
