import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 이분그래프 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bf.readLine());
        /*
        3 2
        1 3
        2 3

        1,2   3


        1 2 3 4

        1 3    2 4
        1 2  , 3 4
         */

        for(int t =0; t <tc ; t++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            ArrayList [] arr = new ArrayList[n+1];
            for(int i=1; i<=n; i++){
                arr[i] = new ArrayList<Integer>();
            }

            for(int i=0; i<r; i++){
                st = new StringTokenizer(bf.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                arr[s].add(e);
                arr[e].add(s);
            }

            int check [] = new int [n+1];
            int flag = 1;
            for(int i =1; i<=n ; i++){
                if(check[i]==0) {
                    check[i]=1;
                    if(!bfs(arr, i, check, n)) {
                        flag=0;
                        break;
                    }

                }
            }
            if(flag==1){
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }

    }

    private static boolean bfs(ArrayList<Integer>[] arr, int start, int[] check, int n) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        while (!queue.isEmpty()){
            int current =queue.poll();
            //System.out.println("current"+current);
            for (int j=0; j<arr[current].size() ; j++){
                int end = arr[current].get(j);

                if(check[end]==0){
                    if(check[current]==1){
                        check[end]=2;
                    }
                    else
                        check[end]=1;

                    queue.add(end);
                }
                if(check[current]==check[end]){
                    return false;
                }
            }
        }
        //System.out.println(Arrays.toString(check));
        return true;
    }

    private static void print(int[][] map) {
        for(int [] tmp: map){
            System.out.println(Arrays.toString(tmp));
        }
    }
}
