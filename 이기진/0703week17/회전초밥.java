package d202307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int ans = -1;
        int arr[] = new int [n*2];
        for(int i=0; i<n; i++) {
            arr[i]=Integer.parseInt(bf.readLine());
            arr[i+n]=arr[i];
        }
        //System.out.println(Arrays.toString(arr));
        for(int start =0; start<arr.length; start++) {
            boolean visit[] = new boolean [d+1];
            int cnt = 0;
            int count = 0;
            int i = start;
            while(count<k) {
                if(!visit[arr[i]]) {
                    visit[arr[i]]=true;
                    cnt++;
                }
                i++;
                if(i==arr.length) {
                    break;
                }
                count++;
            }
            if(!visit[c]) {
                cnt++;
            }
            //System.out.println(start+ " "+ cnt );
            ans=Math.max(cnt, ans);
        }
        System.out.println(ans);
    }
}
