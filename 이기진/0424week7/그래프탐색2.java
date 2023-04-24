import sun.nio.cs.ext.MacHebrew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 그래프탐색2 {
    static int map [][];
    static int ans [];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int [n+1][n+1];
        for(int i =0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            map[s][e]= 1;
            map[e][s]= 1;
        }
         ans  = new int[n];
        Arrays.fill(ans,987654321);
        int q = Integer.parseInt(bf.readLine());
        for(int i=0 ; i<q; i++){
            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            map[s][e]=1;
            map[e][s]=1;
            bfs(1);
            for(int j = 0; j<ans.length; j++){
                if(ans[j]==987654321){
                    System.out.print(-1+" ");
                }
                else {
                    System.out.print(ans[j] + " ");
                }
            }
            System.out.println();
        }
    }

    private static void bfs(int sp) {
        Queue<int[]> queue = new LinkedList<>();
        boolean check[] = new boolean[map.length];
        queue.add(new int[]{0, sp, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            ans[current[1]-1] = Math.min(ans[current[1]-1],current[2]);
            for (int i = 1; i < map.length; i++) {
                if (map[current[1]][i] == 1 && !check[i]) {
                    check[i] = true;
                    queue.add(new int[]{current[1], i, current[2] + 1});
                }
            }
        }
    }

}
