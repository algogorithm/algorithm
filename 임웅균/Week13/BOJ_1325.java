package Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1325 {
    static int N, M, maxCnt;
    static List<List<Integer>> listMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N개의 컴퓨터
        N = Integer.parseInt(st.nextToken());
        // 신뢰하는 관계의 개수
        M = Integer.parseInt(st.nextToken());
        listMap = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            listMap.add(new ArrayList<>());
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            // 신뢰하는 관계가 A B
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            listMap.get(B).add(A);
        }
        // 가장 많은 컴퓨터를 해킹했을때의 카운트
        maxCnt = 0;
        // 컴퓨터 번호별로 해킹가능한 컴퓨터 대수 저장
        int[] cntArray = new int[N+1];
        // 1번컴퓨터부터 N번컴퓨터까지 모두 bfs수행
        for(int computerNum = 1; computerNum <= N; computerNum++){
            int tempCnt = bfs(computerNum, new boolean[N+1]);
            maxCnt = Math.max(maxCnt, tempCnt);
            cntArray[computerNum] = tempCnt;
        }
        String result = "";
        for(int i = 1; i <= N; i++){
            if(maxCnt == cntArray[i]){
                result += i + " ";
            }
        }
        System.out.println(result.trim());
    }

    private static int bfs(int computerNum, boolean[] v) {
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(computerNum);
        v[computerNum] = true;
        while(!q.isEmpty()){
            int currComputerNum = q.poll();
            for(int nextComputerNum : listMap.get(currComputerNum)){
                if(v[nextComputerNum] == false){
                    v[nextComputerNum] = true;
                    q.add(nextComputerNum);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}