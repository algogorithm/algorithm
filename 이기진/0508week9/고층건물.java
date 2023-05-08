package d202305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 고층건물 {
    static long arr[] ;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int ans =0;
        arr= new long [n+1];
        for(int i =1; i<=n; i++){
            arr[i]= Integer.parseInt(st.nextToken());
        }


        for(int i=1; i<=n ;i++){
            //System.out.println(i +" "+ getCount(i,-1)+ " "+ getCount(i,1) );
            int sum = getCount(i,-1)+getCount(i,1);
            ans = Math.max(sum,ans);
        }
        System.out.println(ans);
    }

    private static int getCount(int top, int flag) {

        int cnt =0;
        if(flag==1){
            if(top==1){
                return 0;
            }
            int current = top;
            double a  = Integer.MAX_VALUE;

            while (true){
                current--;

                if(a> (arr[top]-arr[current])*1.0/(top-current) ){
                    a = (arr[top]-arr[current])*1.0/(top-current);
                    cnt++;
                }

                if(current==1){
                    break;
                }
            }

            return cnt;
        }
        else{
            if(top==arr.length-1){
                return 0;
            }
            int current = top;
            double a  = Integer.MIN_VALUE;

            while (true){
                current++;

                if(a< (arr[top]-arr[current])*1.0/(top-current) ){
                    a = (arr[top]-arr[current])*1.0/(top-current);

                    cnt++;
                }


                if(current==arr.length-1){
                    break;
                }

            }

            return cnt;
        }

    }

}
