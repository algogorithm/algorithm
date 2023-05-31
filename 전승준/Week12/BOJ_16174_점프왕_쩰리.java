
package Week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16174_점프왕_쩰리 {
	static int[] dr = {0,1};
	static int[] dc = {1,0};
	static int[][] MAP;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		MAP = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(0, 0, N);
	}
	
	private static void bfs(int sr, int sc, int N) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] v = new boolean[N][N];
		q.add(new int[] {sr, sc});
		v[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			
			if(MAP[poll[0]][poll[1]] == -1) {
				System.out.println("HaruHaru");
				return;
			}
			
			for(int d=0; d<2; d++) {
				int nr = poll[0] + MAP[poll[0]][poll[1]] * dr[d];
				int nc = poll[1] + MAP[poll[0]][poll[1]] * dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<N && !v[nr][nc]) {
					v[nr][nc] = true;
					q.add(new int[] {nr, nc});
				}
			}
		}
		System.out.println("Hing");
	}

}
