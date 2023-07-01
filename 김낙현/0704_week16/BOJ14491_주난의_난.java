import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ14491_주난의_난 {
    static class Wave implements Comparable<Wave> {
        int r;
        int c;
        int timestamp;

        Wave(int r, int c, int timestamp) {
            this.r = r;
            this.c = c;
            this.timestamp = timestamp;
        }

        @Override
        public int compareTo(Wave o) {
            return this.timestamp - o.timestamp;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int fromR = Integer.parseInt(st.nextToken()) - 1;
        int fromC = Integer.parseInt(st.nextToken()) - 1;
        int toR = Integer.parseInt(st.nextToken()) - 1;
        int toC = Integer.parseInt(st.nextToken()) - 1;

        char[][] map = new char[r][];
        for(int row = 0; row < r; ++row) {
            map[row] = br.readLine().toCharArray();
        }

        boolean[][] reached = new boolean[r][c];
        reached[fromR][fromC] = true;
        PriorityQueue<Wave> pq = new PriorityQueue<>();
        pq.add(new Wave(fromR, fromC, 1));

        int answer = -1;

        final int[] DR = {-1, +1, -0, +0};
        final int[] DC = {-0, +0, -1, +1};

        while(!pq.isEmpty()) {
            Wave cur = pq.poll();
            for(int dir = 0; dir < DR.length; ++dir) {
                int nextR = cur.r + DR[dir];
                int nextC = cur.c + DC[dir];

                if(nextR < 0 || nextR >= r || nextC < 0 || nextC >= c || reached[nextR][nextC]) continue;
                if(nextR == toR && nextC == toC) answer = cur.timestamp;
                reached[nextR][nextC] = true;
                pq.add(new Wave(nextR, nextC, map[nextR][nextC] == '0' ? cur.timestamp : cur.timestamp + 1));
            }
        }

        System.out.println(answer);
    }
}
