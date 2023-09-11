package week24_0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1941 {
	//소문난 칠공주
	static char[][] student = new char[5][5];
	static Point[] point = new Point[25];
	static boolean[] v = new boolean[25];
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int idx=0;
		for(int r=0; r<5; r++) {
			student[r] = br.readLine().toCharArray();
			for(int c=0; c<5; c++) {
				point[idx++] = new Point(r, c);
			}
		}
		
//		print(student);
		
		//25개에서 7개를 뽑아
		//나온 조합에서
		//s가 4이상, 가로 세로가 인접한지 조건 판단
		answer = 0;
		combination(0, 0, null, 0);
		
		System.out.println(answer);
	}

	private static void combination(int idx, int cnt, Point p, int sCnt) {
		//조합 7개면
		if(cnt==7) {
			//s>=4이면 bfs로 연결되어 있는지 탐색
			if(sCnt>=4 && check(p)) {
				answer++;
			}
			return;
		}
		
		for(int i=idx; i<point.length; i++) {
			if(!v[i]) {
				v[i] = true;
				p = point[i];
				
				if(student[p.r][p.c] == 'S') {
					student[p.r][p.c] = 'A';
					combination(i, cnt+1, p, sCnt+1);
					student[p.r][p.c] = 'S'; //되돌려
				} else {
					student[p.r][p.c] = 'A';
					combination(i, cnt+1, p, sCnt);
					student[p.r][p.c] = 'Y'; //되돌려
				}
				
				v[i] = false; //되돌려
			}
		}
		
	}

	//가로세로 인접
	private static boolean check(Point p) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(p);
		boolean[][] v = new boolean[5][5];
		v[p.r][p.c] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			int cr = now.r; int cc = now.c;
			
			if(cnt==7) return true;
			
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				//범위 방문 선택한 학생
				if(nr<0 || nr>=5 || nc<0 || nc>=5) continue;
				if(v[nr][nc]) continue;
				if(student[nr][nc]!='A') continue;
				
				q.add(new Point(nr, nc));
				v[nr][nc] = true;
				cnt++;
			}
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

}
