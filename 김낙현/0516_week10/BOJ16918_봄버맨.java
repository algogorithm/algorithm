import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16918_봄버맨 {
    final static int[] DR = {-1, +1, -0, +0};
    final static int[] DC = {-0, +0, -1, +1};
    static Bomb[][] map;

    static class Bomb {
        int r;
        int c;
        int plantedTime;

        public Bomb(int r, int c, int plantedTime) {
            this.r = r;
            this.c = c;
            this.plantedTime = plantedTime;
        }

        private boolean isExplorable(int curTime) {
            return (curTime - 3) == plantedTime;
        }

        public void explode(int curTime) {
            if(!isExplorable(curTime)) return;
            for (int dir = 0; dir < DR.length; ++dir) {
                int targetR = this.r + DR[dir];
                int targetC = this.c + DC[dir];
                if (targetR < 0 || targetR >= map.length || targetC < 0 || targetC >= map[0].length || map[targetR][targetC] == null) continue;
                map[targetR][targetC] = map[targetR][targetC].isExplorable(curTime) ? map[targetR][targetC] : null;
            }
            map[this.r][this.c] = null;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int curTime = 0;
        // init map
        map = new Bomb[r][c];
        for (int row = 0; row < r; ++row) {
            String line = br.readLine();
            for (int col = 0; col < c; ++col) {
                if (line.charAt(col) == '.') continue;
                map[row][col] = new Bomb(row, col, curTime);
            }
        }
        // do nothing first second
        ++curTime;
        while (true) {
            // plant bomb
            if(curTime >= n) break;
            plantBomb(++curTime);
            // explode bomb planted before 3 secs
            if(curTime >= n) break;
            explodeBomb(++curTime);
        }
        printMap(curTime);
    }

    public static void plantBomb(int curTime) {
        for (int r = 0; r < map.length; ++r) {
            for (int c = 0; c < map[0].length; ++c) {
                if (map[r][c] != null) continue;
                map[r][c] = new Bomb(r, c, curTime);
            }
        }
    }

    public static void explodeBomb(int curTime) {
        for (int r = 0; r < map.length; ++r) {
            for (int c = 0; c < map[0].length; ++c) {
                Bomb bomb = map[r][c];
                if (bomb == null) continue;
                bomb.explode(curTime);
            }
        }
    }

    public static void printMap(int curTime) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < map.length; ++r) {
            for (int c = 0; c < map[0].length; ++c) {
                sb.append((map[r][c] == null) ? "." : "O");
            }
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}
