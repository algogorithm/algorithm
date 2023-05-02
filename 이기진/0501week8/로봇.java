import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Arrays;
        import java.util.StringTokenizer;

public class 로봇 {

    static int r;
    static int c;
    static int map[][];
    static int dr[] = {0,-1,1,0,0};
    static int dc[] = {0,0,0,-1,1};
    public static void main(String[] args) throws IOException {

        BufferedReader bf=  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        int k = Integer.parseInt(bf.readLine());
        for(int i =0; i<k; i++){
            st = new StringTokenizer(bf.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            map[sr][sc]=1;
        }
        st = new StringTokenizer(bf.readLine());

        int startr = Integer.parseInt(st.nextToken());
        int startc = Integer.parseInt(st.nextToken());

        map[startr][startc]=1;
        int dir []  = new int [4];
        st = new StringTokenizer(bf.readLine());

        for(int i =0; i< 4; i++) {
            int dir1 = Integer.parseInt(st.nextToken());
            dir[i]=dir1;
        }
        int flag = 0;
        int cnt = 0;
        while(true) {
            int nrow = startr + dr[dir[cnt]];
            int ncol = startc + dc[dir[cnt]];
            if (nrow >= 0 && nrow < r && ncol >= 0 && ncol < c && map[nrow][ncol] == 0) {
                map[nrow][ncol] = 1;
                startr = nrow;
                startc = ncol;
                flag=0;
            }
            else{
                cnt = (cnt+1) %4;
                flag++;
            }
            if(flag==4){
                break;
            }
            /*
            System.out.println(startr+" "+startc);
            for(int [] tmp: map){
                System.out.println(Arrays.toString(tmp));
            }*/

        }
        System.out.println(startr+" "+startc);
    }
}
