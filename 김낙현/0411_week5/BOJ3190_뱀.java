import java.io.*;
import java.util.*;

public class BOJ3190_뱀 {

    static final int[] DR = {-1, +0, +1, -0};
    static final int[] DC = {-0, +1, -0, -1};

    static final int MAX_PLAYTIME = 10_001;
    enum GameObject {
        WALL,
        ROAD,
        APPLE,
        SNAKE,
    }

    static class SnakeDieException extends RuntimeException {};

    static class Snake {

        Deque<int[]> body;
        int headDirection; // up: 0, right: 1, down: 2, left: 3

        public Snake init() {
            this.body = new LinkedList<>();
            headDirection = 1;
            this.body.addFirst(new int[]{1, 1});
            map[1][1] = GameObject.SNAKE;
            return this;
        }

        public Snake move() throws Exception {
            int nextHeadRow = body.peekFirst()[0] + DR[headDirection];
            int nextHeadCol = body.peekFirst()[1] + DC[headDirection];

            if (map[nextHeadRow][nextHeadCol] == GameObject.WALL
                || map[nextHeadRow][nextHeadCol] == GameObject.SNAKE) {
                throw new SnakeDieException();
            } else if (map[nextHeadRow][nextHeadCol] == GameObject.APPLE) {
                eat(nextHeadRow, nextHeadCol);
            } else if (map[nextHeadRow][nextHeadCol] == GameObject.ROAD) {
                go(nextHeadRow, nextHeadCol);
            } else {
                throw new Exception();
            }

            return this;
        }

        public void eat(int appleRow, int appleCol) {
            map[appleRow][appleCol] = GameObject.SNAKE;
            body.addFirst(new int[]{appleRow, appleCol});
        }

        public void go(int nextHeadRow, int nextHeadCol) {
            int[] tail = body.pollLast();
            map[tail[0]][tail[1]] = GameObject.ROAD;
            body.addFirst(new int[]{nextHeadRow, nextHeadCol});
            map[nextHeadRow][nextHeadCol] = GameObject.SNAKE;
        }

        public void turnTo(int dir) {
            headDirection = (headDirection + dir + 4) % 4;
        }
    }

    static GameObject[][] map;
    static Snake snake;
    static int[] dir;
    static int playTime = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        // init map
        map = new GameObject[n + 2][n + 2];
        for (int r = 0; r < map.length; ++r) {
            Arrays.fill(map[r], GameObject.ROAD);
        }
        for(int r = 0; r < map.length; ++r) {
            map[r][0] = map[r][map.length - 1] = map[0][r] = map[map.length
                - 1][r] = GameObject.WALL;
        }

        // init snake;
        snake = new Snake().init();

        // init apple
        for (int i = 0; i < k; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int appleR = Integer.parseInt(st.nextToken());
            int appleC = Integer.parseInt(st.nextToken());
            map[appleR][appleC] = GameObject.APPLE;
        }

        // init direction
        dir = new int[MAX_PLAYTIME];
        int l = Integer.parseInt(br.readLine());
        for(int i = 0; i < l; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            dir[t] = (st.nextToken().equals("D")? 1 : -1);
        }

        // play
        for(; playTime < MAX_PLAYTIME; ++playTime) {
            try {
                snake.move().turnTo(dir[playTime]);
            } catch (SnakeDieException e) {
                System.out.println(playTime);
                return;
            }
        }
    }
}
