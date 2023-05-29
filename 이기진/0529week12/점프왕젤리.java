package d202305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 점프왕젤리 {

    //3
    //1 1 10
    //1 5 1
    //2 2 -1

    static int dr [] = {1,0};
    static int dc [] = {0,1};
    static int map[][];
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n  = Integer.parseInt(bf.readLine());

        map= new int [n][n];

        for (int i =0 ; i< n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j =0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        if(startbfs()){
            System.out.println("HaruHaru");
        }
        else{
            System.out.println("Hing");
        }

    }

    private static boolean startbfs() {
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {0,0});
        boolean visit[][] = new boolean[n][n];
        while(!queue.isEmpty()) {
            int current[] = queue.poll();
            for (int i = 0; i < 2; i++) {
                //System.out.println(Arrays.toString(current));
                if (map[current[0]][current[1]] == -1) {
                    return true;
                }
                int nrow = current[0] + map[current[0]][current[1]] * dr[i];
                int ncol = current[1] + map[current[0]][current[1]] * dc[i];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < n && !visit[nrow][ncol]) {
                    queue.add(new int[]{nrow, ncol});
                    visit[nrow][ncol] = true;
                }

            }
        }
        return false;
    }
}
