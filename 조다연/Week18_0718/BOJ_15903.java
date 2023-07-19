package week18_0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15903 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Long> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			pq.add(Long.parseLong(st.nextToken()));
		}
		
		for(int i=0; i<m; i++) {
			//���� �� ���� �����ְ� sum�� �ι� �־��ֱ�(ī�� ���)
			long sum = pq.poll() + pq.poll();
			pq.add(sum);
			pq.add(sum);
		}
		
		//���� ī�� �� ��� ���ϱ�
		long answer = 0;
		while(!pq.isEmpty()) {
			answer += pq.poll();
		}
		
		System.out.println(answer);
	}

}
