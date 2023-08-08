package week21_0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1303 {
	//전쟁 - 전투
	static int N, M;
	static char[][] map;
	static boolean[][] v;
	//					상하좌우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int w = 0; int b = 0;
		
		v = new boolean[N][M];
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(!v[r][c]) {
					int cnt = bfs(r,c);
					cnt *= cnt; //N제곱 위력
					if(map[r][c]=='W') {
						w += cnt;
					} else {
						b += cnt;
					}
				}
			}
		}

		System.out.println(w+" "+b);
	}

	private static int bfs(int r, int c) {
		int cnt = 1; //병사 수
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {r,c});
		v[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int cr = now[0]; int cc = now[1];
			char color = map[cr][cc];
			
			//4방 탐색
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				//범위 방문 체크
				if(check(nr, nc) && !v[nr][nc]) {
					//같은 팀 병사일 때
					if(color == map[nr][nc]) {
						cnt++;
						q.add(new int[] {nr,nc});
						v[nr][nc] = true;
					}
				}
			}
		}
		
		return cnt;
	}

	private static boolean check(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) {
			return true;
		}
		return false;
	}

}
