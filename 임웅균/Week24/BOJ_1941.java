package Week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1941 {
    static char[][] map = new char[5][5];
    static int[][] pointArr = new int[25][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int point = 0;
        // map에 값 추가
        for (int r = 0; r < 5; r++) {
            input = br.readLine();
            for (int c = 0; c < 5; c++) {
                map[r][c] = input.charAt(c);
                pointArr[point++] = new int[]{r, c};
            }
        }
        /*
            문제조건
            1. 7명의 구성
            2. 7명은 서로 가로나 세로로 인접
            3. S가 4개 이상
            문제풀이
            1. 지도의 각 위치들(pointArr), 총 25곳의 위치들에서 7개의 조합을 뽑는다.
            2. 각 조합 안에 S가 4개 이상이 있는지 파악한다.
            3. 7개의 위치가 서로 가로나 세로로 인접해 있는지 판단.
         */
        System.out.println(combination(0, 0, new int[7], new boolean[25]));
    }

    private static int combination(int start, int depth, int[] selected, boolean[] booleans) {
        if (depth == 7) {
            // S 개수 확인
            int sCnt = 0;
            for (int i = 0; i < 7; i++) {
                int r = pointArr[selected[i]][0];
                int c = pointArr[selected[i]][1];
                sCnt += map[r][c] == 'S' ? 1 : 0;
            }
            if (sCnt < 4) return 0;

            // 서로 가로세로로 이웃하는지 확인
            boolean isAdjacent = bfs(selected);
            if (isAdjacent) {
                return 1;
            } else {
                return 0;
            }
        }
        int count = 0;
        for (int i = start; i < 25; i++) {
            if (!booleans[i]) {
                booleans[i] = true;
                selected[depth] = i;
                count += combination(i + 1, depth + 1, selected, booleans);
                booleans[i] = false;
            }
        }
        return count;
    }

    private static boolean bfs(int[] selected) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[7]; // 선택된 7명에 대한 방문 여부
        int count = 0; // 방문한 학생 수

        // 첫번째 학생을 시작점으로 설정
        q.add(pointArr[selected[0]]);
        visited[0] = true;
        count++;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            // 인접한 학생을 찾아서 큐에 추가
            for (int i = 0; i < 7; i++) {
                if (!visited[i]) {
                    int[] next = pointArr[selected[i]];
                    // 현재의 위치(curr)와 다음의 위치(next)가 인접한지 확인
                    // x 좌표 또는 y 좌표의 차이가 정확히 1인지 확인
                    if (Math.abs(curr[0] - next[0]) + Math.abs(curr[1] - next[1]) == 1) {
                        q.add(next);
                        visited[i] = true;
                        count++;
                    }
                }
            }
        }
        return count == 7;
    }
}
