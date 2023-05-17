import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2583_영역_구하기 {
    final static int[] DX = {-0, +0, -1, +1};
    final static int[] DY = {-1, +1, -0, +0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] grid = new boolean[M][N];

        for (int i = 0; i < K; ++i) {
            st = new StringTokenizer(br.readLine(), " ");

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int y = y1; y < y2; ++y) {
                Arrays.fill(grid[y], x1, x2, true);
            }
        }

        Map<String, Object> answer = solve(grid);

        System.out.println(answer.get("numOfArea"));
        for (Object area : (Object[]) answer.get("area")) {
            System.out.print(area + " ");
        }
    }

    static Map<String, Object> solve(boolean[][] grid) {
        int numOfArea = 0;
        LinkedList<Integer> area = new LinkedList<Integer>();
        for (int y = 0; y < grid.length; ++y) {
            for (int x = 0; x < grid[0].length; ++x) {
                if (grid[y][x]) continue;
                ++numOfArea;
                Map<String, Object> areaStatus = getAreaStatusFrom(x, y, grid);
                area.add((Integer) areaStatus.get("size"));
                grid = (boolean[][]) areaStatus.get("updated");
            }
        }

        Map<String, Object> answer = new HashMap<>();
        answer.put("numOfArea", numOfArea);
        answer.put("area", area.stream().sorted().toArray());
        return answer;
    }

    static Map<String, Object> getAreaStatusFrom(int x, int y, boolean[][] grid) {
        int area = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        grid[y][x] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; ++dir) {
                int nextX = cur[0] + DX[dir];
                int nextY = cur[1] + DY[dir];

                if (nextX < 0 || nextX >= grid[0].length || nextY < 0 || nextY >= grid.length || grid[nextY][nextX])
                    continue;

                q.add(new int[]{nextX, nextY});
                grid[nextY][nextX] = true;
                ++area;
            }
        }

        Map<String, Object> status = new HashMap<>();
        status.put("size", area);
        status.put("updated", grid);

        return status;
    }
}
