package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_15591_MooTube {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		HashMap<Integer, LinkedList<int[]>> map = new HashMap<Integer, LinkedList<int[]>>();

		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			if(!map.containsKey(p))	map.put(p, new LinkedList<int[]>());
			if(!map.containsKey(q)) map.put(q, new LinkedList<int[]>());
			map.get(p).add(new int[]{q,r});
			map.get(q).add(new int[]{p,r});
		}
		
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cnt = 0;
			boolean visit[] = new boolean[N+1];
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.offer(v);
			visit[v] = true;
			
			while(!queue.isEmpty()) {
				int p = queue.poll();
				LinkedList<int[]> list = map.get(p);
				for(int q[] : list) {
					if(!visit[q[0]] && q[1] >= k) {
						queue.add(q[0]);
						visit[q[0]] = true;
						cnt++;
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		
		System.out.print(sb);
	}
}