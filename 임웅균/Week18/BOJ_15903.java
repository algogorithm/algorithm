package Week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15903 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        while(st.hasMoreTokens()){
            pq.add(Long.parseLong(st.nextToken()));
        }
        for(int t = 0; t < m; t++){
            Long temp = pq.poll() + pq.poll();
            pq.add(temp);
            pq.add(temp);
        }
        long result = 0;
        for(long i : pq){
            result += i;
        }
        System.out.println(result);
    }
}
