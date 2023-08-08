package d202308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class 전쟁_전투 {
    static int dr [] = {-1,1,0,0};
    static int dc [] = {0,0,1,-1};
    static int mcnt = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        char map [][]= new char[n][m];

        for(int i =0; i<n; i++){
            String tmp = bf.readLine();
            for(int j=0; j<m; j++){
                map[i][j]=tmp.charAt(j);
            }
        }

        boolean check[][] = new boolean[n][m];
        int team = 0;
        int enemy = 0;
        for(int i =0; i<n; i++){
            for(int j=0; j<m;  j++){
                    if(!check[i][j]) {
                        check[i][j] = true;
                        dfs(map, i, j, check);
                        //System.out.println(mcnt);
                        if (map[i][j] == 'W') {
                            check[i][j] = true;
                            team = (int) (team + Math.pow(mcnt, 2));
                        } else if (map[i][j] == 'B') {
                            check[i][j] = true;
                            enemy = (int) (enemy + Math.pow(mcnt, 2));
                        }
                        mcnt = 1;
                    }
                }

            }
        System.out.println(team+" "+enemy);
    }

    private static void dfs(char[][] map, int cr, int cc, boolean[][] check) {


        for(int i=0;  i<4; i++){
            int nr = cr+dr[i];
            int nc = cc+dc[i];

            if(nr>=0 && nr<map.length && nc>=0 && nc<map[0].length && map[nr][nc]==map[cr][cc] && !check[nr][nc] ){
                check[nr][nc]=true;
                dfs(map,nr,nc,check);
                mcnt++;
            }
        }

    }
}
