import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 나무재태크 {
    static int map [][];
    static int sply [][];
    static int n;
    static int m;
    static int k;
    static int dr []  = {-1,1,0,0,-1,1,-1,1};
    static int dc []  = {0,0,-1,1,-1,1,1,-1};
    static ArrayList<tree> tl;
    static ArrayList<tree> deadtl;
    static class tree implements Comparable<tree>{
        int row;
        int col;
        int age;
        int status;

        public tree(int row, int col, int age, int status) {
            this.row = row;
            this.col = col;
            this.age = age;
            this.status = status;
        }

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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public int compareTo(tree o) {
            return this.age-o.age;
        }

        @Override
        public String toString() {
            return "tree{" +
                    "row=" + row +
                    ", col=" + col +
                    ", age=" + age +
                    ", status=" + status +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st  = new StringTokenizer(bf.readLine());

         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
         k = Integer.parseInt(st.nextToken()); // k년 후이니까

        map = new int[n][n];
        sply = new int [n][n];
        for(int i =0; i< n; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<n; j++){
                sply[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tl = new ArrayList<>();
        deadtl = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            tl.add(new tree(r-1,c-1,age,1));

        }
        Collections.sort(tl);
        initmap(map);
        //printmap(map);

        for(int i =1; i<=k*4; i++ ){
            int season = i%4;
            //봄

            if(season ==1){
                spring();
            }
            //여름
            else if(season==2){
                summer();
            }
            //가을
            else if(season==3){
                fall();
            }
            //겨울
            else{
                winter();
            }

        }
        int ans = 0;
       for(int i =0; i<tl.size(); i++){
           if(tl.get(i).status==1){
               ans++;
           }
       }
        System.out.println(ans);
    }




    //봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다. 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
    // 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
    // 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
    private static void spring() {
        // 어린 나무 부터 양분을 먹으니까 나이순으로 정렬을 해야한다.
        //  Collections.sort(tl);

        for (int i =0; i<tl.size(); i++){
            tree current = tl.get(i);
            if(caneat(current)){
                map[current.row][current.col]-=current.age;
                current.age+=1;
            }
            else{
                current.status=2;
                tl.remove(current);
                i--;
                deadtl.add(current);
            }
        }



    }

    //여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
    // 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
    // 소수점 아래는 버린다.

    private static void summer() {
        for(int i =0; i<deadtl.size(); i++){
            tree current = deadtl.get(i);
            deadtl.remove(current);
            map[current.row][current.col] += (current.age/2);
        }

    }

    //가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
    // 어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
    // 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.

    private static void fall() {
        ArrayList<tree> newtl = new ArrayList<>();
        for(int i =0; i<tl.size(); i++){
            tree current = tl.get(i);
            if(current.age %5 ==0 && current.status==1){

                for(int k=0; k<8; k++){
                    int nrow = current.row+dr[k];
                    int ncol = current.col+dc[k];

                    if(nrow>=0 && nrow<n && ncol>=0 && ncol<n){
                        newtl.add(new tree(nrow,ncol,1,1));
                    }
                }
            }
        }
        tl.addAll(0,newtl);
    }

    private static void winter() {
        for(int i =0 ; i<n; i++){
            for(int j =0; j<n; j++){
                map[i][j]+=sply[i][j];
            }
        }
    }

    private static boolean caneat(tree current) {
        int r = current.row;
        int c = current.col;
        int age = current.age;
        int status =current.status;

        if(status == 1 && map[r][c]>=age){
            return true;
        }
        return false;


    }

    private static void printmap(int[][] map) {
        for(int [] tmp : map){
            System.out.println(Arrays.toString(tmp));
        }
    }

    private static void initmap(int[][] map) {
        for(int i =0; i<map.length; i++){
            for (int j=0; j<map[0].length; j++){
                map[i][j] = 5;
            }
        }
    }
}
