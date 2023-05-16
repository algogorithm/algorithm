package week10_0516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15591 {
	//MooTube
	static int N, Q;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//N까지 영상 존재, 영상끼리 가는 경로 무조건 존재, N-1개의 쌍
		//Spannig Tree
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		List<int[]>[] adj = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) adj[i] = new ArrayList<>();
		
		//농부 존이 직접 잰 두 동영상 쌍 USADO
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			adj[p].add(new int[] {q,r});
			adj[q].add(new int[] {p,r});
		}
		
		StringBuilder sb = new StringBuilder();
		//농부 존의 Q개의 질문
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			boolean[] visit = new boolean[N+1];
			visit[v] = true;
			Queue<Integer> q = new LinkedList<>();
			q.add(v);
			
			int cnt = 0;
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				for(int[] arr : adj[cur]) {
					if(!visit[arr[0]] && arr[1]>=k) {
						q.add(arr[0]);
						visit[arr[0]] = true;
						cnt++;
					}
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb.toString());
	}

}
