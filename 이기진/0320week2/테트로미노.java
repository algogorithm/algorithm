import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 테트로미노 {
    static int max ;
    static int dr [] = {-1,1,0,0};
    static int dc [] = {0,0,1,-1};
    static int r;
    static int c;
    static int map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader bf  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        max =0;
        map= new int [r][c];
        for(int i=0; i<r ; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<c ; j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        boolean [][] visit = new boolean[r][c];
        for(int i=0; i<r ; i++){
            for(int j=0; j<c ; j++){
             //   System.out.println("start"+"----------------"+ i+"--------------"+j);

                visit[i][j]=true;
                startdfs(i,j,visit,1,map[i][j]);
                visit[i][j]=false;
                checkh(i,j);
            }
        }
        /*
        for(int [] tmp : map){
            System.out.println(Arrays.toString(tmp));
        }*/
        System.out.println(max);
    }

    private static void checkh(int row, int col) {
        // ㅏ
        //  1 -1 0 0
        //  0  0 0 1
        //  ㅜ
        //  1 0 0 0
        //  0 0 -1 1
        // ㅓ
        //  1 -1 0 0
        //  0 0  -1 0
        // ㅗ
        // 0 -1 0 0
        // 0 0 -1 1

        // ㅏ
        //
        int [][] mrow = {{1,-1,0,0},{1,0,0,0},{1,-1,0,0},{0,-1,0,0}};
        int [][] mcol = {{0,0,0,1},{0,0,-1,1},{0,0,-1,0},{0,0,-1,1}};
        for(int i =0; i<4; i++){
            int hsum = 0;
            for(int j =0; j<4; j++){
                int nrow = row+mrow[i][j];
                int ncol = col+mcol[i][j];
                if(nrow>=0 && nrow<r && ncol>=0 && ncol<c){
                    hsum =hsum+map[nrow][ncol];
                }
                else{
                    break;
                }
            }
            max= Math.max(hsum,max);
        }
    }

    private static void startdfs(int row, int col, boolean[][] visit,int cnt,int sum) {
       // System.out.println(row + " "+ col + " " +sum+" cnt"+cnt);
/*
        for(boolean[] tmp : visit){
            System.out.println(Arrays.toString(tmp));
        }*/
        if(cnt == 4) {
            max = Math.max(sum,max);
            return;
        }

        for(int i=0; i<4 ; i++){

            int nrow = row+dr[i];
            int ncol = col+dc[i];

            //System.out.println("nrow " + nrow+ "ncol "+ ncol);
            if(nrow>=0 && nrow<r && ncol >=0 && ncol<c){
                if(!visit[nrow][ncol]){
                    visit[nrow][ncol]=true;
                    startdfs(nrow,ncol,visit,cnt+1,sum+map[nrow][ncol]);
                    visit[nrow][ncol]=false;
                }
            }

        }



    }
}
