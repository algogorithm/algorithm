import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3187_양치기_꿍 {

    static final int[] DR = {-1, +1, -0, +0};
    static final int[] DC = {-0, +0, -1, +1};

    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int r = 0; r < R; ++r) {
            map[r] = br.readLine().toCharArray();
        }

        int numOfLamb = 0;
        int numOfWolf = 0;

        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if(map[r][c] == '#' || visited[r][c]) continue;
                int[] subAnswer = getSurvivedLambAndWolves(r, c);
                numOfLamb += subAnswer[0];
                numOfWolf += subAnswer[1];
            }
        }

        System.out.println(numOfLamb + " " + numOfWolf);
    }

    static public int[] getSurvivedLambAndWolves(int r, int c) {
        int numOfLamb = 0;
        int numOfWolf = 0;

        Queue<int[]> q = new LinkedList<>();
        visited[r][c] = true;
        q.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] loc = q.poll();
            if (map[loc[0]][loc[1]] == 'v') {
                ++numOfWolf;
            } else if (map[loc[0]][loc[1]] == 'k') {
                ++numOfLamb;
            }

            for (int dir = 0; dir < 4; ++dir) {
                int nextR = loc[0] + DR[dir];
                int nextC = loc[1] + DC[dir];

                if (nextR < 0 || nextR >= map.length || nextC < 0 || nextC >= map[0].length
                    || map[nextR][nextC] == '#' || visited[nextR][nextC]) {
                    continue;
                }
                visited[nextR][nextC] = true;
                q.add(new int[]{nextR, nextC});
            }
        }

        if (numOfLamb > numOfWolf) {
            numOfWolf = 0;
        } else {
            numOfLamb = 0;
        }

        return new int[]{numOfLamb, numOfWolf};
    }
}
