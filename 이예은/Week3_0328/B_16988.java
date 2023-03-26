package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16988 {
	static int r[] = {0,0,1,-1};
	static int c[] = {1,-1,0,0};
	static int N, M, ans = 0, map[][];
	
	public static void baduk(int idx) {
		if(idx == 2) {
			ans = Math.max(ans, check());
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					baduk(idx+1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	public static int check() {
		boolean visit[][] = new boolean[N][M];
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 2 && !visit[i][j]) {
					visit[i][j] = true;
					cnt += count(visit, i, j);
				}
			}
		}
		
		return cnt;
	}
	
	public static int count(boolean visit[][], int i, int j) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {i,j});
		int cnt = 0, wall = 0;
		
		while(!queue.isEmpty()) {
			int b[] = queue.poll();
			cnt++;
			
			for (int d = 0; d < 4; d++) {
				int dr = b[0] + r[d];
				int dc = b[1] + c[d];
				
				if(dr >= 0 && dr < N && dc >= 0 && dc < M) {
					if(map[dr][dc] == 2 && !visit[dr][dc]) {
						visit[dr][dc] = true;
						queue.offer(new int[] {dr, dc});
					} else if (map[dr][dc] == 0)	wall++;
				}
			}
		}
		
		return wall > 0 ? 0 : cnt ;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		baduk(0);
		
		System.out.print(ans);	
	}
}