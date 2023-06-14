package Week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1325_효율적인_해킹 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] graph = new ArrayList[N+1];
		int[] availableCount = new int[N+1];
		
		for(int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph[B].add(A);
		}

		for(int start=1; start<N+1; start++) {
			if(graph[start].isEmpty()) continue;
			
			Queue<Integer> q = new LinkedList<>();
			boolean[] v = new boolean[N+1];
			q.offer(start);
			v[start] = true;
			int cnt = 0;
			
			while(!q.isEmpty()) {
				int now = q.poll();
				
				for(int next : graph[now]) {
					if(v[next]) continue;
					
					q.offer(next);
					++cnt;
					v[next] = true;
				}
			}
			availableCount[start] = cnt;
		}
		
		int max = Arrays.stream(availableCount).max().getAsInt();

		StringBuilder sb = new StringBuilder();
		for(int i=1; i<availableCount.length; i++) {
			if(availableCount[i] == max) {
				sb.append(i).append(" ");				
			}
		}
		System.out.println(sb);
	}

}
