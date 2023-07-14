package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_15903 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			pq.add(Long.parseLong(st.nextToken()));
		}
		
		while(M-- > 0) {
			long a = pq.poll() + pq.poll();
			pq.add(a);
			pq.add(a);
		}
		
		long answer = 0;
		while(!pq.isEmpty()) {
			answer += pq.poll();
		}
		
		System.out.print(answer);
	}
}