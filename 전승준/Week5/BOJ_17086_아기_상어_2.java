package Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17086_아기_상어_2 {
	static class Shark {
		int r;
		int c;
		int dist;
		int dir;
		
		public Shark(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		public Shark(int r, int c, int dist, int dir) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.dir = dir;
		}
	}
	static int[] dr = {-1,-1,-1,0,0,1,1,1};
	static int[] dc = {-1,0,1,-1,1,-1,0,1};
	static int[][] MAP;
	static int res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		MAP = new int[N][M];
		List<Shark> inputShark = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
				inputShark.add(new Shark(i, j, 0, -1));
			}
		}
		
		bfs(N, M, inputShark);
		
		System.out.println(res);
	}
	private static void bfs(int N, int M, List<Shark> inputShark) {
		for(int idx=0; idx<inputShark.size(); idx++) {
			Queue<Shark> sQ = new LinkedList<>();
			boolean[][] v = new boolean[N][M];
			Shark curShark = inputShark.get(idx);
			sQ.add(curShark);
			v[curShark.r][curShark.c] = true;
			
			while(!sQ.isEmpty()) {
				Shark sh = sQ.poll();
				
				System.out.println("r:"+sh.r+" c:"+sh.c+" dist:"+sh.dist);
				
				res = Math.max(res, sh.dist);
				
				if(sh.dir == -1) {
					for(int d=0; d<8; d++) {
						int nr = sh.r + dr[d];
						int nc = sh.c + dc[d];
						if(nr>=0 && nr<N && nc>=0 && nc<M && !v[nr][nc]) {
							v[nr][nc] = true;
							sQ.add(new Shark(nr, nc, sh.dist+1, d));
						}
					}
				} else {
					int nr = sh.r + dr[sh.dir];
					int nc = sh.c + dc[sh.dir];
					
					if(nr>=0 && nr<N && nc>=0 && nc<M && !v[nr][nc]) {
						v[nr][nc] = true;
						sQ.add(new Shark(nr, nc, sh.dist+1, sh.dir));
					}
				}
			}
		}
	}
}
