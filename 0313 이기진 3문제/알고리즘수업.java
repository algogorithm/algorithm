import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 알고리즘수업 {
    static int n;
    static int m;
    static int cnt =0;
    static int arr [];
    static int tmp [];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());

        arr = new int [n];
        tmp = new int [n];
        for(int i =0; i<n; i++){
            arr[i]= Integer.parseInt(st.nextToken());
        }
        //Arrays.sort(arr);
        merge_sort(0,n-1);
        if(cnt<m){
            System.out.println(-1);
        }
    }


    static void merge_sort(int p , int q){

        if(p<q){
            int len = (p+q)/2;
            merge_sort(p,len);
            merge_sort(len+1,q);
            merge( p, len, q);
            //System.out.println(Arrays.toString(arr));
        }
    }

    private static void merge(int p, int q, int r) {

        int i = p;
        int j = q+1;
        int t = 0;


        while(i<=q && j<=r){
            if(arr[i]<=arr[j]){
                tmp[t++]= arr[i++];
               }
            else{
                tmp[t++]=arr[j++];
               }
        }
        while (i<=q)
            tmp[t++] = arr[i++];
        while (j<=r)
            tmp[t++] = arr[j++];

        i=p;
        t=0;
        while(i<=r){
            cnt++;
            arr[i++] = tmp[t++];
            if(cnt == m) {
                System.out.println(arr[i - 1]);
                System.exit(0);
            }
        }
        //System.out.println(Arrays.toString(tmp));

    }
}
