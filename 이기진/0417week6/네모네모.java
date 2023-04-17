import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 네모네모 {
    static int m;
    static int n;
    static int ans =0;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int map [][] = new int[m][n];

        startdfs(map,0,0);
        System.out.println(ans);
    }

    private static void startdfs(int[][] map, int r, int c) {
        if(r== m-1 && c==n-1){
            map[r][c]=0;
            if(checkmap(map)){

                ans++;
                //System.out.println(ans);
            }
            map[r][c]=1;
            if(checkmap(map)){

                ans++;
                //System.out.println(ans);
            }
            return;
        }
        //System.out.println(r+" "+c);
        if(c==n-1) {
            map[r][c]=0;
            startdfs(map, r+1, 0);
            map[r][c] = 1;
            startdfs(map, r+1, 0);
        }
        else{
            map[r][c]=0;
            startdfs(map, r, c+1);
            map[r][c] = 1;
            startdfs(map, r, c+1);
        }

    }

    private static boolean checkmap(int[][] map) {
        for(int i =0; i<m-1 ; i++){
            for(int j=0; j<n-1 ; j++){
                if(map[i][j]==1 && map[i][j]==map[i+1][j] && map[i][j]== map[i][j+1] && map[i][j]==map[i+1][j+1] ){
                    return false;
                }
            }

        }
        return true;
    }
}
