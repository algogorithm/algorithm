package d202307;

import java.io.*;
import java.util.*;

public class 카드합체놀이 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.offer((long) Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {

            long aCard = pq.poll();
            long bCard = pq.poll();
            long cardSum = aCard + bCard;

            pq.offer(cardSum);
            pq.offer(cardSum);
        }

        long sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }

        System.out.println(sum);
    }
}