package Week20;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1976 {
    // 각 도시간의 연결 상태를 저장하는 배열
    static int[][] cityConnections;
    // 각 도시의 루트를 저장하는 배열
    static int[] cityRoots, travelPlan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 도시의 수 N과 여행 계획에 포함된 도시의 수 M을 입력 받음
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        cityConnections = new int[N + 1][N + 1];
        travelPlan = new int[M + 1];
        cityRoots = new int[N + 1];
        StringTokenizer st;
        // 도시간의 연결 상태를 입력 받음
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                cityConnections[r][c] = Integer.parseInt(st.nextToken());
            }
            // 초기에는 각 도시의 루트는 자기 자신
            cityRoots[r] = r;
        }
        // 여행 계획에 포함된 도시들을 입력 받음
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            travelPlan[i] = Integer.parseInt(st.nextToken());
        }
        // 도시간 연결 상태에 따라 Union 연산 수행
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (cityConnections[r][c] == 1) {
                    union(r, c);
                }
            }
        }
        // 첫번째 여행 도시의 루트를 찾음
        int firstCityRoot = find(travelPlan[1]);
        // 모든 여행 도시가 첫번째 도시와 같은 루트(즉, 연결된)를 가지는지 확인
        for (int i = 2; i < travelPlan.length; i++) {
            if (firstCityRoot != find(travelPlan[i])) {
                bw.write("NO");
                bw.close();
                return;
            }
        }
        // 모든 도시가 연결되어 있으면 "YES"를 출력
        bw.write("YES");
        bw.close();
    }

    // Union 연산: 두 도시를 연결. 두 도시의 루트 중 하나를 다른 하나의 자식으로 만듦
    private static void union(int city1, int city2) {
        city1 = find(city1);
        city2 = find(city2);
        if (city1 != city2) {
            cityRoots[city2] = city1;
        }
    }

    // Find 연산: 주어진 도시의 루트를 찾음
    private static int find(int city) {
        if (city == cityRoots[city]) {
            // city의 루트가 자신이면, 그것은 대표 노드(루트 노드)이다.
            return city;
        } else {
            // city의 루트가 자신이 아니라면, city의 루트 노드를 찾기 위해 재귀적으로 find 함수를 호출한다.
            // 이 때, city의 부모 노드를 그 루트 노드로 바로 연결하는 경로 압축을 수행한다.
            cityRoots[city] = find(cityRoots[city]);
            return cityRoots[city];
        }
//        return city == cityRoots[city] ? city : (cityRoots[city] = find(cityRoots[city]));
    }
}
