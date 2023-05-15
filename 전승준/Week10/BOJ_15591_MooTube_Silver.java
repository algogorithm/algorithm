package Week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15591_MooTube_Silver {
	static List<int[]>[] USADO;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		USADO = new LinkedList[N+1];
		for(int i=1; i<N+1; i++) {
			USADO[i] = new LinkedList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			USADO[p].add(new int[] {q, r});
			USADO[q].add(new int[] {p, r});
		}
		
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			findMooTube(k, v);
		}
	}
	private static void findMooTube(int k, int v) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visit = new boolean[USADO.length];
		q.add(v);
		visit[v] = true;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int currVtx = q.poll();
			
			for(int i=0; i<USADO[currVtx].size(); i++) {
				int oppoVtx = USADO[currVtx].get(i)[0];
				int oppoWeight = USADO[currVtx].get(i)[1];
				
				if(!visit[oppoVtx] && oppoWeight >= k) {
					q.add(oppoVtx);
					visit[oppoVtx] = true;
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

}
