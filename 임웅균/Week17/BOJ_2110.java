package Week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2110 {
    static int N, C;
    static int[] houses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];
        for(int i = 0; i < N; i++){
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);
        // 이분탐색으로 적절한 거리값을 탐색한다.
        // 탐색할 거리 범위 지정
        // 최소범위값는 0
        int minDistance = 0;
        // 최대범위값는 가장 끝값 - 첫값
        int maxDistance = houses[N-1] - houses[0];
        int result = 0;
        while(minDistance <= maxDistance){
            int selectedDistance = (minDistance + maxDistance) / 2;
            // 가장 첫 위치는 무조건 공유기를 설치한다.
            int cnt = 1;
            // 거리 계산을 위해 이전집을 저장한다.
            int prevHouse = houses[0];
            // 현재 탐색중인 거리 mid를 기준으로, 설치할 수 있는 집을 탐색
            for(int i = 1; i < N; i++){
                if(houses[i] - prevHouse >= selectedDistance){
                    cnt++;
                    prevHouse = houses[i];
                }
            }
            /*
                C를 찾았더라도 계속 탐색하는 이유:
                처음 찾은 저 값이 꼭 최적의 해라는 보장이 없기 때문에,
                전체 탐색한 후 지속해서 최적값을 갱신해준다.
             */
            // 현재 설치 가능한 공유기 개수가 C개 이상이라면?
            if(cnt >= C){
                result = selectedDistance;
                minDistance = selectedDistance + 1;
            }
            // 현재 설치 가능한 공유기 개수가 C개 미만이라면?
            else {
                maxDistance = selectedDistance - 1;
            }
        }
        System.out.println(result);
    }
}
//6 4
//0 3 4 7 10 9