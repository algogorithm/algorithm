import java.util.Scanner;
import java.util.*;

public class BJ2234 {
    static int N, M;
    static int[][] map;
    static int[] dr = { 0, -1, 0, 1 };
    static int[] dc = { -1, 0, 1, 0 };
    static int[] check = { 1, 2, 4, 8 };
    static int[][] visited;
    static int[][] area;
    static ArrayList<Integer> areaSize = new ArrayList<>();
    static int brokenMax;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        visited = new int[M][N]; // 방문 여부저장
        area = new int[M][N]; // 영역

        int cnt = 0; // 영역 개수
        int maxSize = 0; // 가장 큰 영역 크기

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) { // 방문하지 않은 영역
                    visited[i][j] = 1; // 방문 표시
                    cnt++; // 영역 개수 증가

                    Queue<Pos> q = new LinkedList<>();
                    q.add(new Pos(i, j));
                    int size = 0; // 영역 크기

                    while (!q.isEmpty()) {
                        Pos now = q.poll();
                        int wall = map[now.r][now.c]; // 벽 상태

                        area[now.r][now.c] = cnt; // 번호로 표시
                        size++; // 영역 크기 증가

                        for (int k = 3; k >= 0; k--) {
                            int nr = now.r + dr[k];
                            int nc = now.c + dc[k];

                            if (wall / check[k] > 0) {
                                wall = wall % check[k];
                                continue;
                            }

                            if (nr < 0 || nr >= M || nc < 0 || nc >= N)
                                continue;

                            if (visited[nr][nc] == 1)
                                continue;

                            visited[nr][nc] = 1; // 방문 표시
                            q.add(new Pos(nr, nc)); // 큐에 추가
                        }
                    }

                    if (size > maxSize)
                        maxSize = size; // 갱신

                    areaSize.add(size); // 리스트에 저장
                }
            }
        }

        brokenMax = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];

                    if (nr < 0 || nr >= M || nc < 0 || nc >= N)
                        continue;

                    if (area[i][j] != area[nr][nc]) { // 서로 다른 영역이면
                        int sum = areaSize.get(area[i][j] - 1);
                        sum += areaSize.get(area[nr][nc] - 1);

                        if (sum > brokenMax)
                            brokenMax = sum; // 최대 파괴 가능한 벽 크기 갱신
                    }
                }
            }
        }

        System.out.println(cnt); // 영역 개수
        System.out.println(maxSize); // 가장 큰 영역 크기
        System.out.println(brokenMax); // 최대 파괴 가능한 벽 크기
    }

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

