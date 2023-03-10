import java.io.*;
import java.util.*;

public class BOJ11279_최대_힙 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; ++i) {
            int nextInt = Integer.parseInt(br.readLine());
            if (nextInt == 0) {
                System.out.println(pq.isEmpty() ? 0 : pq.poll());
            } else {
                pq.add(nextInt);
            }
        }
    }
}
