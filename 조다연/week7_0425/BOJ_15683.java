package week7_0425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15683 {
	//감시
	static int N, M, answer;
	static int[][] map, tmp;
	static ArrayList<CCTV> cctv;
	//				      우    좌      상    하
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {1, -1, 0, 0};


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		
		//사무실 가로 N, 세로 M
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctv = new ArrayList<>();
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = N*M;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				//cctv
				if(map[r][c] >= 1 && map[r][c] <= 5) {
					cctv.add(new CCTV(r, c, map[r][c]));
				}
			}
		}
		
		dfs(0);
		System.out.println(answer);
//		print(map);
	}

	private static void dfs(int level) {
		//모든 cctv 봤으면
		if(level == cctv.size()) {
			//답 구하기
			int cnt = 0;
			
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if(map[r][c] == 0) cnt++;
				}
			}
			
			answer = Math.min(answer, cnt);
			return;
		}
		
		//90도 회전하면서 방향 탐색
		rotate(level);
	}

	private static void rotate(int level) {
		if(level == cctv.size()) return;
		
		int r = cctv.get(level).r;
		int c = cctv.get(level).c;
		int type = cctv.get(level).cam;
		
		switch (type) {
		case 1: //1: 우(0), 좌(1), 상(2), 하(3)
			for(int d=0; d<4; d++) {
				watch(r, c, d);
				dfs(level+1);
				back(r, c, d);
			}
			break;
		
		case 2: //2: 우좌(01), 상하(23)
			for(int d=0; d<=2; d+=2) {
				watch(r, c, d);
				watch(r, c, d+1);
				dfs(level+1);
				back(r, c, d);
				back(r, c, d+1);
			}
			break;
		
		case 3: //3: 우상(02), 우하(03), 좌하(13), 좌상(12)
			for(int i=0; i<2; i++) {
				for(int j=2; j<=3; j++) {
					watch(r, c, i);
					watch(r, c, j);
					dfs(level+1);					
					back(r, c, i);
					back(r, c, j);
				}
			}
			break;
		
		case 4: //4: 우좌상(012), 상우하(023), 우하좌(013), 하좌상(123)
			//012 013 
			//023 123 -> 230 231
			for(int i=2; i<=3; i++) {
				watch(r, c, 0);
				watch(r, c, 1);
				watch(r, c, i);
				dfs(level+1);
				back(r, c, 0);
				back(r, c, 1);
				back(r, c, i);
			}
			
			for(int i=0; i<=1; i++) {
				watch(r, c, 2);
				watch(r, c, 3);
				watch(r, c, i);
				dfs(level+1);
				back(r, c, 2);
				back(r, c, 3);
				back(r, c, i);
			}
			break;
			
		default: //5: 우좌상하(0123)
			for(int d=0; d<4; d++) {
				watch(r, c, d);
			}
			dfs(level+1);
			for(int d=0; d<4; d++) {
				back(r, c, d);
			}
			break;
		}
		return;
	}

	private static void back(int r, int c, int d) {
		int nr = r;
		int nc = c;
		
		while(true) {
			nr += dr[d];
			nc += dc[d];
			
			//범위를 벗어나거나 벽을 만나면
			if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc] == 6) break;
			
			//원상복구
			map[nr][nc]++; 
		}
		return;
	}

	private static void watch(int r, int c, int d) {
		int nr = r;
		int nc = c;
		
		while(true) {
			nr += dr[d];
			nc += dc[d];
			
			//범위를 벗어나거나 벽을 만나면
			if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc] == 6) break;
			
			//-1로 감시 가능 구역 표시
			map[nr][nc]--; 
		}
		return;
	}

	static class CCTV {
		int r;
		int c;
		int cam;
		
		public CCTV(int r, int c, int cam) {
			super();
			this.r = r;
			this.c = c;
			this.cam = cam;
		}
	}

	private static void print(int[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
