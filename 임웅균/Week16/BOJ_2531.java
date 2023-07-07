package Week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 회전 초밥 벨트에 놓인 접시의 수
        int N = Integer.parseInt(st.nextToken());
        // 초밥의 가짓수
        int d = Integer.parseInt(st.nextToken());
        // 연속해서 먹는 접시의 수
        int k = Integer.parseInt(st.nextToken());
        // 쿠폰 번호
        int c = Integer.parseInt(st.nextToken());
        //  N개의 줄에는 벨트의 한 위치부터 시작하여 회전 방향을 따라갈 때 초밥의 종류
        // 1 이상 d 이하의 정수가 각 줄마다 하나씩 주어짐
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        // 슬라이딩 윈도우 내부에서 초밥 종류별로 선택된 횟수 저장하는 배열
        int[] check = new int[d + 1];
        // 서로다른 초밥이 선택된 갯수 저장
        int kind = 0;
        /*
            문제 해결 핵심
            서로 다른 초밥의 종류를 슬라이딩 윈도우로 계산해라.
         */
        // 슬라이딩 윈도우를 위한 첫 계산
        for(int i = 0; i < k; i++){
            kind += ++check[arr[i]] == 1 ? 1 : 0;
        }
        // 최대값 계산
        int max = check[c] == 0 ? kind + 1 : kind;
        // 슬라이딩 윈도우 계산
        for(int i = k; i < N+k; i++){
            // 위 계산 후 추가되는 초밥과 제거되는 초밥에 대한 계산
            kind += (++check[arr[i % N]] == 1 ? 1 : 0) + (--check[arr[(i - k) % N]] == 0 ? -1 : 0);
            max = Math.max(max, check[c] == 0? kind+1 : kind);
        }
        System.out.println(max);
    }
}