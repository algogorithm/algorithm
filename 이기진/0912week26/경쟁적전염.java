package d202309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 경쟁적전염 {
    static int dr [] = {-1,1,0,0};
    static int dc [] = {0,0,-1,1};
    static class Point implements Comparable<Point>{
        int row;
        int col;
        int val;

        int cycle;

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Point(int row, int col, int val, int cycle) {
            this.row = row;
            this.col = col;
            this.val = val;
            this.cycle =cycle;
        }

        @Override
        public int compareTo(Point o) {

            if(o.cycle==this.cycle){
                return this.val-o.val;
            }
            return this.cycle-o.cycle;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    ", val=" + val +
                    ", cycle=" + cycle +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int map [][] =  new int[n+1][n+1];

        for(int i =1; i<=n; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=1; j<=n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(bf.readLine());
        int s = Integer.parseInt(st.nextToken());
        int er = Integer.parseInt(st.nextToken());
        int ec = Integer.parseInt(st.nextToken());
        gobfs(map,s);
        /*
        System.out.println(s);
        for(int i =1; i<=n; i++){
            for(int j =1; j<=n ; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

         */
        System.out.println(map[er][ec]);
    }

    private static void gobfs(int[][] map, int s) {
        PriorityQueue<Point> queue = new PriorityQueue<Point>();
        boolean visit [][] = new boolean[map.length][map.length];
        for(int i =1; i<map.length ;i++) {
            for (int j = 1; j < map.length; j++) {
                if (map[i][j] != 0) {
                    visit[i][j]=true;
                    queue.add(new Point(i, j, map[i][j], 0));
                }
            }
        }

        //System.out.println(queue);

        for(int cnt = 0; cnt<s ; cnt++) {
            int size = queue.size();
            //System.out.println(size);
            for (int c = 0; c < size; c++) {
                Point current = queue.poll();
               // System.out.println("current"+current);

                    for (int i = 0; i < 4; i++) {
                        int nrow = current.row + dr[i];
                        int ncol = current.col + dc[i];
                        if (nrow >= 1 && nrow < map.length && ncol >= 1 && ncol < map.length && !visit[nrow][ncol]) {
                            visit[nrow][ncol] = true;
                            map[nrow][ncol] = current.val;
                            queue.add(new Point(nrow, ncol, current.val,current.cycle+1));
                        }
                    }


            }
            /*
            for(int i =1; i< map.length; i++){
                for(int j =1; j<map.length ; j++){
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }*/
        }

    }
}
