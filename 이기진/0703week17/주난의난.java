package d202307;

import java.util.*;
import java.io.*;

class Point implements Comparable<Point>{
    int x, y, cnt;
    public Point(int x, int y, int cnt){
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
    public int compareTo(Point o){
        return this.cnt - o.cnt;
    }
}

public class 주난의난{
    static int n, m, x2, y2;
    static char[][] map;
    static boolean[][] check;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());
        map = new char[n+1][m+1];
        check = new boolean[n+1][m+1];
        for(int i=1; i<=n; i++){
            String str = br.readLine();
            for(int j=1; j<=m; j++){
                map[i][j] = str.charAt(j-1);
            }
        }
        bfs(sr, sc);
    }
    static void bfs(int sr, int sc){
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(sr, sc, 0));
        check[sr][sc] = true;
        while(!pq.isEmpty()){
            Point now = pq.poll();
            if(now.x==x2 && now.y==y2){
                System.out.println(now.cnt);
                return;
            }
            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx<=0 || ny<=0 || nx>n || ny>m || check[nx][ny]) continue;
                check[nx][ny] = true;
                if(map[nx][ny]=='0') pq.add(new Point(nx, ny, now.cnt));
                else pq.add(new Point(nx, ny, now.cnt+1));
            }
        }
    }
}
