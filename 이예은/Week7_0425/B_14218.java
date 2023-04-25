package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_14218 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean city[][] = new boolean[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			city[c1][c2] = true;
			city[c2][c1] = true;
		}
		
		int q = Integer.parseInt(br.readLine());
		while(q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			city[c1][c2] = true;
			city[c2][c1] = true;
			int cities[] = new int[N+1];
			boolean visit[] = new boolean[N+1];
			Queue<int[]> queue = new LinkedList<int[]>();
			queue.offer(new int[] {1, 1});
			visit[1] = true;
			
			while(!queue.isEmpty()) {
				int c[] = queue.poll();
				for(int i=2; i<=N; i++) {
					if(city[c[0]][i] && !visit[i]) {
						visit[i] = true;
						cities[i] = c[1];
						queue.offer(new int[] {i, c[1]+1});
					}
				}
			}
			
			sb.append(0);
			for(int i=2; i<=N; i++) {
				sb.append(" ").append(cities[i] == 0 ? -1 : cities[i]);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}