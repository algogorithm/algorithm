package d202308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 알고스팟 {
    static class edge implements Comparable<edge>{
        int r;
        int c;
        int weight;
        public edge(int r, int c, int weight) {
            super();
            this.r = r;
            this.c = c;
            this.weight = weight;
        }
        public int compareTo(edge o1) {
            return this.weight-o1.weight;
        }

    }
    static int row, col;
    static int[][] map;
    static int [] dr = {-1,1,0,0};
    static int [] dc = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        for(int i=0; i<row; i++) {
            String s = bf.readLine();
            for(int j=0; j<col; j++) {
                map[i][j] = s.charAt(j)-'0';
            }
        }
        bfs();

    }
    private static void bfs() {
        PriorityQueue<edge> que = new PriorityQueue<>();
        boolean [][] visit = new boolean[row][col];
        que.add(new edge (0,0,0));
        visit[0][0]=true;
        while(!que.isEmpty()) {
            edge current = que.poll();
            //System.out.println(current.r+" "+current.c+" "+current.weight);

            if(current.c==col-1 && current.r==row-1) {
                System.out.println(current.weight);
                break;
            }
            for(int i =0; i<4; i++) {
                int nrow = current.r+dr[i];
                int ncol = current.c+dc[i];
                int cnt = current.weight;
                if(nrow >=0 && nrow<row && ncol>=0 && ncol<col && !visit[nrow][ncol] ) {
                    visit[nrow][ncol]=true;
                    if(map[nrow][ncol]==0) {
                        que.add(new edge (nrow,ncol,cnt) );
                    }
                    else {
                        que.add(new edge (nrow,ncol,cnt+1));
                    }
                }
            }
        }
    }
    static void print(int [][]map) {
        for(int tmp[] : map) {
            System.out.println(Arrays.toString(tmp));
        }
    }
}
