package d202305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 영역구하기 {
    static int r, c;
    static int map[][];
    static int dr []  = {-1,0,1,0};
    static int dc [] = {0,1,0,-1};
    static int cnt =0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(bf.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            //System.out.println(r1 + " " + r2);
            //System.out.println(c1 + " " + c2);
            for (int a = r1; a < r2; a++) {
                for (int b = c1; b < c2; b++) {
                    //System.out.println("check " + a + " " + b);
                    map[a][b] = 1;
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[][] visit = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visit[i][j] && map[i][j]==0) {
                    cnt=1;
                    visit[i][j]=true;
                    dfs(i, j, visit);
                    ans.add(cnt);
                }
            }
        }
        Collections.sort(ans);
        System.out.println(ans.size());
        for(int t: ans) {
            System.out.print(t+" ");
        }
    }

    private static void dfs(int row, int col, boolean[][] visit) {
        for(int i =0; i<4; i++) {
            int nr = row+dr[i];
            int nc = col+dc[i];
            if(nr>=0 && nr<r && nc>=0 && nc<c && map[nr][nc]==0 && !visit[nr][nc]) {
                visit[nr][nc]=true;
                dfs(nr,nc,visit);
                cnt++;
            }
        }

    }

    static void print(int map[][]) {
        for (int tmp[] : map) {
            System.out.println(Arrays.toString(tmp));
        }
    }
}