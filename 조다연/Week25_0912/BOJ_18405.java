package week25_0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_18405 {
	//경쟁적 전염
	static int N, K;
	static int[][] map;
	static boolean[][] v;    
	static boolean[][] a;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static ArrayList<ArrayList<Point>> virus;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		//초기화 - 각 번호 바이러스 좌표 저장
		virus = new ArrayList<>();
		for(int i=0; i<=K; i++) {
			virus.add(new ArrayList<Point>());
		}
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c]!=0) {
					virus.get(map[r][c]).add(new Point(r,c));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); 
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());		

		//전체적으로 한 번의 증식을 마친 바이러스 자리 표시
		a = new boolean[N][N];
		while(S-- > 0) {
			v = new boolean[N][N]; //해당 초에 방문 표시
			
			for(int idx=1; idx<=K; idx++) {
				for(int i=0; i<virus.get(idx).size(); i++) {
					//바이러스 증식 시작되는 좌표
					int r = virus.get(idx).get(i).r;
					int c = virus.get(idx).get(i).c;
					
					if(v[r][c]) continue;
					if(a[r][c]) continue;
					
					//증식해라
					increase(r, c, idx);
					
					//하다가 바이러스가 이미 증식 되었다면 굳이 더 볼 필요 없삼
					if(map[X-1][Y-1]!=0) S=0;
				}
			}
		}
		
		
		//S초 후 (X,Y)에 위치한 바이러스 출력
		System.out.println(map[X-1][Y-1]);
	}

	private static void increase(int r, int c, int idx) {
		v[r][c] = true;
		a[r][c] = true;
		
		//상하좌우로 증식
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			//범위 방문
			if(nr<0 || nr>=N || nc<0 || nc>=N || v[nr][nc]) continue;
			
			//빈 곳이면 바이러스 증식
			//나와 같은 바이러스면 아무것도 하즤마 또 번져야 될 수도 있응까
			//다른 바이러스 있으면 증식 못ㅌ해
			if(map[nr][nc]==0) {
				map[nr][nc] = idx;
				v[nr][nc] = true;
				//virus에 값 넣어주셈
				virus.get(idx).add(new Point(nr,nc));
			}
		}
	}
	
	static class Point {
		int r;
		int c;
		
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}
