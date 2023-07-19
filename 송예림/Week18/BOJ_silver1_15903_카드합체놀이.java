package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class silver1_15903_카드합체놀이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			pq.offer(Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < m; i++) {
			int a = pq.poll();
			int b = pq.poll();
			pq.offer(a+b);
			pq.offer(a+b);
		}
		
		long result = 0;
		for (Integer i : pq) {
			result += i;
		}
		
		System.out.println(result);
	}

}