package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_17086 {
	static int r[] = {-1,0,1,0,1,1,-1,-1};
	static int c[] = {0,1,0,-1,1,-1,1,-1};
	static int N, M, S[][];
	
	public static int search(int i, int j) {
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean visit[][] = new boolean[N][M];
		visit[i][j] = true;
		queue.offer(new int[] {i, j, 0});
		
		while(!queue.isEmpty()) {
			int[] shark = queue.poll();
			
			for(int d=0; d<8; d++) {
				int dr = shark[0] + r[d];
				int dc = shark[1] + c[d];
				if(dr >= 0 && dr < N && dc >= 0 && dc < M && !visit[dr][dc]) {
					if(S[dr][dc] == 1)	return shark[2]+1;
					else {
						visit[dr][dc] = true;
						queue.offer(new int[] {dr, dc, shark[2]+1});
					}
				}
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new int[N][M];
		int ans = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) 
				S[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(S[i][j] == 0) {
					ans = Math.max(ans, search(i, j));
				}
			}
		}
		
		System.out.print(ans);
	}
}