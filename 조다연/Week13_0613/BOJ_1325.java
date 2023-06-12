package week13_0613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1325 {
	//효율적인 해킹
	static int N, M;
	static ArrayList<Integer>[] graph;
	static boolean[] v;
	static int[] cnt;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//초기화
		cnt = new int[N+1];
		graph = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			//방향 (a가 b를 신뢰해 -> b가 해킹되면 a도 해킹가능)
			graph[b].add(a);
		}
		
		for(int i=1; i<=N; i++) {
			haking(i);
		}
		
		int max = 0;
		for(int i=1; i<=N; i++) {
			max = Math.max(max, cnt[i]);
			System.out.print(i+"번째 "+cnt[i]+" ");
		}
		
		for(int i=1; i<=N; i++) {
			if(cnt[i]==max) System.out.print(i+" ");
		}
	}

	private static void haking(int i) {
		v = new boolean[graph.length];
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(i);
		v[i] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next : graph[cur]) {
				if(!v[next]) {
					cnt[i]++;
					q.add(next);
					v[next] = true;
				}
			}
		}
	}
}
