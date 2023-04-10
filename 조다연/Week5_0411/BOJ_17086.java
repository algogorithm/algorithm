package week5_0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17086 {
	//17086. 아기 상어2
	static int[][] map;
	static boolean[][] v;
	static int N,M;
	//					좌상 상 우상 우 우하 하 좌하 좌 
	static int[] dr = {-1,-1,-1,0,1,1, 1, 0};
	static int[] dc = {-1, 0, 1,1,1,0,-1,-1};
	static int answer;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				//현재 칸이 1이면(상어)
				//안전거리는 0이 되므로 할 필요 x
				if(map[r][c] == 1) continue;
				
				int safe = bfs(r,c);
				answer = Math.max(answer, safe);
			}
		}
		System.out.println(answer);
	}

	private static int bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		v = new boolean[N][M];
		v[r][c] = true;
		q.add(new Point(r,c,0));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			//8방탐색
			for(int d=0; d<8; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				//범위 &방문 체크
				if(check(nr, nc) && !v[nr][nc]) {
					//다음 칸이 1이면(상어) 
					//안전거리 끝나는 지점이므로 +1하고 끝내기
					if(map[nr][nc]==1) {
						return p.distance+1;
					}
					
					//아니면 이동
					v[nr][nc] = true;
					q.add(new Point(nr,nc,p.distance+1));
				}
			}
		}
		
		return 0;
	}
	
	private static boolean check(int nr, int nc) {
		if(nr>=0 && nc >=0 && nr<N && nc<M) {
			return true;
		}
		
		return false;
	}

	static class Point {
		int r; 
		int c; 
		int distance;
		
		public Point (int r, int c, int distance) {
			super();
			this.r=r;
			this.c=c;
			this.distance=distance;
		}
	}

}
