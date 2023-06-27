package Week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int idx = 0;
        while(st.hasMoreTokens()){
            arr[idx++] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int answer = 0;
        for(int curr = 0; curr < N; curr++){
            int currNum = arr[curr];
            int start = 0;
            int end = N-1;
            while(start < end){
                int sum = arr[start] + arr[end];
                // 두 수의 합이 현재 찾기위한 수보다 크다면
                if(sum > currNum){
                    // end포인트를 왼쪽으로 옮겨준다.
                    end--;
                }
                // 두 수의 합이 현재 찾기위한 수보다 작다면
                else if(sum < currNum){
                    // start포인트를 오른쪽으로 옮겨준다.
                    start++;
                }
                // 두 수의 합이 현재 찾기위한 수와 같다면
                else{
                    // 두 수엔 0이 들어갈 수 있기 때문에
                    // 우연의 경우라도 두 수에 찾기위한 수가 들어가면 안된다.
                    if(curr == start){
                        start++;
                    }
                    else if(curr == end){
                        end--;
                    }
                    else{
                        answer++;
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
