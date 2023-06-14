package BaekJoon;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class B_1325 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer> num[] = new ArrayList[N];
		int count[] = new int[N];
		int max = 1;
		
		for(int i=0; i<N; i++) {
			num[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken())-1;
			num[B].add(A);
		}
		
		for(int i=0; i<N; i++) {
			Queue<Integer> queue = new LinkedList<Integer>();
			boolean visit[] = new boolean[N];
			visit[i] = true;
			queue.offer(i);
			
			int cnt = 0;
			while(!queue.isEmpty()) {
				int c = queue.poll();
				cnt++;
				for(int j : num[c]) {
					if(!visit[j]) {
						visit[j] = true;
						queue.offer(j);
					}
				}
			}
			
			count[i] = cnt;
			max = Math.max(max, cnt);
		}
		
		for(int i=0; i<N; i++) {
			if(count[i] == max)	sb.append(i+1).append(" ");
		}
		
		System.out.print(sb.toString());
	}
}