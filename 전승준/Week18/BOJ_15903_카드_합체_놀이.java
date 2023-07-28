package Week18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15903_카드_합체_놀이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int countOfCards = Integer.parseInt(st.nextToken());
		int reverses = Integer.parseInt(st.nextToken());
		Queue<Long> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<countOfCards; ++i) {
			pq.add(Long.parseLong(st.nextToken()));
		}
		
		while(reverses-- > 0) {
			long min1st = pq.poll();
			long min2nd = pq.poll();
			
			for(int i=0; i<2; ++i) {
				pq.add(min1st + min2nd);
			}
		}

		System.out.println(pq.stream().reduce(0L, Long::sum));
	}
}
