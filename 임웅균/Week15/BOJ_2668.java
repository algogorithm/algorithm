package Week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_2668 {
    static int[] arr;
    static int N;
    static List<Integer> cycle;

    /*
        첫째 줄에서 숫자를 적절히 뽑으면, 그 뽑힌 정수들이 이루는 집합과, 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치한다.
        이 부분이 이해 안돼서 검색해봄
        검색 후 이게 사이클을 의미한다는것을 알게됨
        모든 수를 탐색하며 사이클인 수를 찾아 출력해준다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // 1 ~ N까지기때문에 N+1로 설정한다.
        arr = new int[N+1];
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        // 반복되는 수를 넣기 위한 리스트
        cycle = new ArrayList<>();
        // 반복문을 통해 모든 수에 대한 사이클 찾기
        for(int i = 1; i <= N; i++){
            // 해당 수로 시작해서 해당 수로 끝나기까지의 과정을 확인하기 위한 방문배열
            boolean[] v = new boolean[N+1];
            // 시작숫자, 현재숫자(지금은 시작숫자), 방문배열을 dfs에 넣고 탐색한다.
            dfs(i, i, v);
        }
        // 작은수부터 출력하기 위한 정렬
        Collections.sort(cycle);
        System.out.println(cycle.size());
        for(int i : cycle){
            System.out.println(i);
        }
    }

    private static void dfs(int startNum, int currNum, boolean[] v) {
        // 종료조건
        // 1. 이번 노드가 방문한 노드인지 확인
        if(v[currNum] == true) {
            // 2. 사이클인지 확인하기 위해 현재수와 끝나는 수가 같은지 확인
            if (startNum == currNum) {
                cycle.add(startNum);
            }
            return;
        }
        // 현재 수를 방문했다는 체크
        v[currNum] = true;
        // currNum을 다음 수로 바꾼 후 다시 재귀
        dfs(startNum, arr[currNum], v);
    }
}
