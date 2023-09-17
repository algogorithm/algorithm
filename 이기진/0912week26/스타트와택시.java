package d202309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스타트와택시 {
    static int N, M, fuel;
    static int[][] map;
    static Passenger[] passengers;
    static Taxi taxi;
    static Queue<Integer>[][] passengerMap;
    static int[] dirRow = {-1, 0, 1, 0};
    static int[] dirCol = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        passengers = new Passenger[M+1];
        passengerMap  = new LinkedList[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N; j++) {
                passengerMap[i][j] = new LinkedList<>();
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    map[i][j] = -1;
                }
            }
        }
        st = new StringTokenizer(bf.readLine());
        int startRow = Integer.parseInt(st.nextToken());
        int startCol = Integer.parseInt(st.nextToken());
        taxi = new Taxi(startRow, startCol, 0);
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(bf.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int dr = Integer.parseInt(st.nextToken());
            int dc = Integer.parseInt(st.nextToken());
            passengerMap[sr][sc].add(i);
            passengers[i] = new Passenger(sr, sc, dr, dc,i);
        }
        for(int i=0;i<M;i++){
            int beforeTime = taxi.time;
            if(!searchPassenger()){
                System.out.println(-1);
                return;
            }
            int srcTime = taxi.time;
            //System.out.println("passenger taxi : "+taxi.row+","+taxi.col+","+(taxi.time-beforeTime)+ "<"+fuel+">");
            int idx= passengerMap[taxi.row][taxi.col].poll();
            if(!goDest(passengers[idx].dr,passengers[idx].dc)){
                System.out.println(-1);
                return;
            }
            // System.out.println("dest taxi : "+taxi.row+","+taxi.col+","+(taxi.time-beforeTime)+ "<"+fuel+">");

            fuel -=(taxi.time - beforeTime);

            if(fuel<0){
                System.out.println(-1);
                return;
            }
            else{
                fuel += (2*(taxi.time - srcTime));
            }
        }
        System.out.println(fuel);
        return;
    }

    private static boolean goDest(int dr, int dc) {
        Queue<Taxi> q = new LinkedList<>();
        q.offer(taxi);
        boolean[][] visited = new boolean[N + 1][N + 1];
        visited[taxi.row][taxi.col] = true;
        while(!q.isEmpty()){
            Taxi cur = q.poll();
            if(cur.row == dr && cur.col==dc){
                taxi = cur;
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nextRow = cur.row + dirRow[i];
                int nextCol = cur.col + dirCol[i];
                if (nextRow < 1 || nextCol < 1 || nextRow > N || nextCol > N || visited[nextRow][nextCol] || map[nextRow][nextCol]==-1)
                    continue;
                visited[nextRow][nextCol] = true;
                q.offer(new Taxi(nextRow,nextCol,cur.time+1));
            }

        }
        return false;
    }

    private static boolean searchPassenger() {
        ArrayList<Taxi> candidatePassengers = new ArrayList<>();
        Queue<Taxi> q = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        q.offer(taxi);
        visited[taxi.row][taxi.col] = true;
        while (!q.isEmpty()) {
            Taxi cur = q.poll();
            if(!candidatePassengers.isEmpty()&& candidatePassengers.get(0).time <cur.time)
                continue;
            if(!passengerMap[cur.row][cur.col].isEmpty()){
                candidatePassengers.add(cur);
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nextRow = cur.row + dirRow[i];
                int nextCol = cur.col + dirCol[i];
                if (nextRow < 1 || nextCol < 1 || nextRow > N || nextCol > N || visited[nextRow][nextCol] || map[nextRow][nextCol]==-1)
                    continue;
                visited[nextRow][nextCol] = true;
                q.offer(new Taxi(nextRow,nextCol,cur.time+1));
            }
        }
        if(candidatePassengers.isEmpty())
            return false;
        Collections.sort(candidatePassengers);
        taxi = candidatePassengers.get(0);
        return true;
    }



    static class Passenger {
        int sr, sc, dr, dc,idx;

        public Passenger(int sr, int sc, int dr, int dc,int idx) {
            this.sr = sr;
            this.sc = sc;
            this.dr = dr;
            this.dc = dc;
            this.idx = idx;
        }
    }

    static class Taxi implements Comparable<Taxi>{
        int row, col, time;

        public Taxi(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public int compareTo(Taxi o) {
            if(this.time == o.time){
                if(this.row == o.row){
                    return this.col - o.col;
                }
                return this.row - o.row;
            }
            return this.time - o.time;
        }
    }
}
