import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16174_점프왕_쩰리 {
    static final int[] DR = {+1, +0};
    static final int[] DC = {+0, +1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());


        int[][] map = new int[n][n];

        for(int r = 0; r < n; ++r) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int c = 0; c < n; ++c) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(isReachable(map) ? "HaruHaru" : "Hing");
    }

    public static boolean isReachable(int[][] map) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        q.add(new int[] {0, 0});
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int[] curLocation = q.poll();

            for(int dir = 0; dir < DR.length; ++dir) {
                int nextR = curLocation[0] + DR[dir]*map[curLocation[0]][curLocation[1]];
                int nextC = curLocation[1] + DC[dir]*map[curLocation[0]][curLocation[1]];

                if(nextR < 0 || nextR >= map.length || nextC < 0 || nextC >= map.length || visited[nextR][nextC]) continue;

                if(map[nextR][nextC] == -1) return true;
                q.add(new int[] {nextR, nextC});
                visited[nextR][nextC] = true;
            }
        }

        return false;
    }
}
