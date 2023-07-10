package Week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1446 {

    static class Shortcut implements Comparable<Shortcut> {
        int start;
        int end;
        int dist;

        public Shortcut(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Shortcut o) {
            return this.start - o.start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 지름길 개수
        int N = Integer.parseInt((st.nextToken()));
        // 고속도로 길이
        int D = Integer.parseInt((st.nextToken()));

        // 지름길 정보를 저장할 배열
        Shortcut[] shortcuts = new Shortcut[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            shortcuts[i] = new Shortcut(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 시작위치를 기준으로 지름길 정렬
        Arrays.sort(shortcuts);

        // 각 위치까지의 최소거리를 저장할 DP배열
        int[] dp = new int[D+1];
        for(int i = 1; i <= D; i++){
            // 기본적으로 이전 위치에서 1만큼 더 거리를 더함 (고속도로를 그대로 갈 경우)
            dp[i] = dp[i-1] + 1;

            // 이제 모든 지름길을 확인해본 뒤 가능한게 있다면 갱신해보자.
            for(Shortcut s : shortcuts) {
                // 지름길 끝 위치가 현재 위치와 같다면?
                if(s.end == i){
                    // 현재 위치까지의 최소 거리와 (지름길의 시작 위치까지의 거리 + 지름길의 거리) 중 작은 것을 선택
                    dp[i] = Math.min(dp[i], dp[s.start] + s.dist);
                }
            }
        }
        System.out.println(dp[D]);
    }
}
