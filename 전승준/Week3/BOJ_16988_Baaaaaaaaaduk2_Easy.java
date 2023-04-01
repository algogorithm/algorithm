package Week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16988_Baaaaaaaaaduk2_Easy {
	static class BadukBall {
		int r;
		int c;
		
		BadukBall(int r, int c){
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "BadukBall [r=" + r + ", c=" + c + "]";
		}
	}
	static int[][] CHECKERBOARD;
	static boolean[][] v;
	static int[] DR = {-1,1,0,0};
	static int[] DC = {0,0,-1,1};
	static int N, M, Ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		CHECKERBOARD = new int[N][M];
		List<BadukBall> list = new ArrayList<>();

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				CHECKERBOARD[i][j] = Integer.parseInt(st.nextToken());
				if(CHECKERBOARD[i][j] == 0) {
					list.add(new BadukBall(i, j));
				}
			}
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				if(CHECKERBOARD[i][j] == 2) {
//					checkerboardBfs(i, j, 2);					
//				}
//			}
//		}
		
		for(int x=0; x<list.size()-1; x++) {
			BadukBall a = list.get(x);
			for(int y=x+1; y<list.size(); y++) {
				BadukBall b = list.get(y);
				CHECKERBOARD[a.r][a.c] = CHECKERBOARD[b.r][b.c] = 1;
				v = new boolean[N][M];
				int sum = 0;
				
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						if(!v[i][j] && CHECKERBOARD[i][j] == 2) {
							sum += checkerboardBfs(i, j, 2);					
						}
					}
				}
				
				Ans = Math.max(Ans, sum);
				CHECKERBOARD[a.r][a.c] = CHECKERBOARD[b.r][b.c] = 0;
			}
		}
		
		System.out.println(Ans);
	}

	private static int checkerboardBfs(int r, int c, int k) {
		Queue<BadukBall> q = new LinkedList<>();
		q.add(new BadukBall(r, c));
		v[r][c] = true;
		int twoCnt = 1;
		boolean check = true;
		
		while(!q.isEmpty()) {
			BadukBall ball = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = ball.r + DR[d];
				int nc = ball.c + DC[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M && !v[nr][nc] && CHECKERBOARD[nr][nc] != 1) {
					if(CHECKERBOARD[nr][nc] == 0) {
						check = false;
					}
					else {
						v[nr][nc] = true;
						q.add(new BadukBall(nr, nc));
						twoCnt++;						
					}
				}
			}
		}
		if(check) return twoCnt;
		else return 0;
	}

}
