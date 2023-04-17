import java.io.*;
import java.util.StringTokenizer;

public class 양치기꿍 {
    static int r;
    static int c;
    static char map[][];
    static int dr[] = {-1,1,0,0};
    static int dc[] = {0,0,-1,1};
    static int vcnt;
    static int kcnt;
    public static void main(String[] args) throws Exception{


        //6 6
        //...#..
        //.##v#.
        //#v.#.#
        //#.k#.#
        //.###.#
        //...###

        BufferedReader bf =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r  = Integer.parseInt(st.nextToken());
        c  = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for(int i=0; i<r; i++){
            String s = bf.readLine();
            for(int j=0; j<c; j++){
                map[i][j]= s.charAt(j);
            }
        }
        boolean check[][] =new boolean[r][c];
        int vans = 0;
        int kans = 0;
        for(int i = 0; i<r; i++){
            for(int j=0 ;j<c ; j++){

                if((map[i][j]=='k'||map[i][j]=='v') && !check[i][j]){
                    vcnt=0;
                    kcnt=0;
                    check[i][j]=true;
                    startdfs(i,j,check);

                    if(vcnt<kcnt){
                        kans+=kcnt;
                    }
                    else {
                        vans+=vcnt;
                    }
                }
            }
        }

        System.out.println(kans+" "+vans);
    }

    private static void startdfs(int sr, int sc, boolean[][] check) {

        if(map[sr][sc]=='v'){
            vcnt++;
        }
        else if (map[sr][sc]=='k'){
            kcnt++;
        }
        for(int i =0; i<4; i++){
            int nrow = sr+dr[i];
            int ncol = sc+dc[i];
            if(nrow>=0 && nrow<r &&ncol>=0 &&ncol<c && map[nrow][ncol] !='#' && !check[nrow][ncol]){
                check[nrow][ncol]=true;
                startdfs(nrow,ncol,check);
            }
        }

    }

}