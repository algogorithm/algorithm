package week6_0418;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3187 {
	//양치기 꿍
	static int R, C;
	static char map[][];
	static boolean v[][];
	// 				    우, 좌, 하, 상
	static int dr[] = {0, 0, -1, 1};
	static int dc[] = {1, -1, 0, 0};
	static int sheep = 0;
	static int wolf = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//가로, 세로
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		v = new boolean[R][C];
		
		//map 입력받기
		for(int r=0; r<R; r++) {
			String s = br.readLine();
			for(int c=0; c<C; c++) {
				map[r][c] = s.charAt(c);
			}
		}
		//print(map);
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				//울타리 제외
				if(map[r][c] != '#' && !v[r][c]) {
					bfs(r,c);
				}
			}
		}
		
		//살아남게 되는 양과 늑대 수 출력
		System.out.println(sheep+" "+wolf);
	}

	
	private static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r,c));
		v[r][c] = true;
		
		int wolfCnt = 0;
		int sheepCnt = 0;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int cr = p.r;
			int cc = p.c;
			
			if(map[cr][cc] == 'v') { //늑대라면
				wolfCnt++;
			} else if(map[cr][cc] == 'k') { //양이라면
				sheepCnt++;
			}
			
			//4방탐색
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				//범위 내 && 노방문 && 노울타리
				if(check(nr,nc) && !v[nr][nc] && map[nr][nc] != '#') {
					q.add(new Point(nr,nc));
					v[nr][nc] = true;
				}
			}
		}
		//양의 숫자가 늑대보다 크면 양만 살아남고
		if(sheepCnt > wolfCnt) {
			sheep += sheepCnt;
		} else { //그 외는 늑대가 살아남음
			wolf += wolfCnt;
		}
	}
	
	private static boolean check(int nr, int nc) {
		if(nr>=0 && nc>=0 && nr<R && nc<C) {
			return true;
		}
		return false;
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

	//잘 들어왔니?
	private static void print(char[][] map) {
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
		
	}

}
