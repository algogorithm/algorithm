package Week16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14497_주난의_난 {
	static int N, M;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static boolean[][] v;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		
		st = new StringTokenizer(br.readLine());
		int[] junan = new int[] {
				Integer.parseInt(st.nextToken()) - 1,
				Integer.parseInt(st.nextToken()) - 1
		};
		int[] thief = new int[] {
				Integer.parseInt(st.nextToken()) - 1,
				Integer.parseInt(st.nextToken()) - 1
		};
		
		
		for(int i=0; i<N; ++i) {
			String str = br.readLine();
			for(int j=0; j<M; ++j) {
				map[i][j] = str.charAt(j);
			}
		}
		
		int cnt = 1;
		while(!bfs(junan, thief, map)) {
			++cnt;
		}

		System.out.println(cnt);
	}

	private static boolean bfs(int[] start, int[] find, char[][] map) {
		Queue<int[]> queue = new LinkedList<>();
		v = new boolean[N][M];
		queue.add(start);
		v[start[0]][start[1]] = true;
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			int cr = poll[0];
			int cc = poll[1];
			
			if(map[cr][cc] == '#') {
				return true;
			}
			
			for(int d=0; d<4; ++d) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M && !v[nr][nc]) {
					if(map[nr][nc] == '1') map[nr][nc] = '0';
					else queue.add(new int[] {nr, nc});
					v[nr][nc] = true;
				}
			}
			
		}
		return false;
	}

}
