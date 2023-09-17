package week25_0912;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19238 {
	//스타트 택시
	static int N, M, fuel;
	static int taxiR, taxiC;
	static int[][] map;
	static boolean[][] v;
	static int[][] person;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		//택시의 첫 위치
		taxiR = Integer.parseInt(st.nextToken());
		taxiC = Integer.parseInt(st.nextToken());
		
		person = new int[M+1][4]; //출발 행,열 / 도착 행,열
		for(int r=1; r<=M; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<4; c++) {
				person[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		//1. 현재 택시 위치에서 가장 가까운 손님 위치 파악
		//2. 승객 위치에서 그 승객의 도착 위치까지의 최단거리 구하고 빼주기
		//연료>=0이면 최단거리x2만큼 연료에 더해주기 
		//123 반복하다가 손님 다 이동 시켰으면 남은 연료 출력
		//연료<0이면 종료 -1 출력
		
		//택시에서 첫번째 손님 태우기
		//최단거리가 가장 짧 -> 행 -> 열
		//person[M][0]person[M][1]
		int p_cnt = M; //태운 사람 수 0 되면 종료
		boolean[] a = new boolean[M+1];
		boolean flag = false;
		
		while(p_cnt>0) {
			//1. 현재 택시 위치에서 가장 가까운 손님 위치 파악
			int d = Integer.MAX_VALUE; int f_r = 0, f_c=0, idx=0;
			for(int i=1; i<=M; i++) {
				
				if(a[i]) continue;
				
				int dt = find(person[i][0], person[i][1]);
				
				//최단거리가 안나오면 갈 수 없다는 뜻
				if(dt==-1) {
					flag = true;
					break;
				}
				
				//거리가 작으면 다가갈 승객의 위치와 거리를 구해놔
				if(d>dt) {
					d = Math.min(d, dt);
					f_r = person[i][0];
					f_c = person[i][1];
					idx = i;
				} else if(d==dt) {
					//최단거리가 같으면 더작은 행 열 번호로
					if(f_r>person[i][0]) {
						f_r = person[i][0];
						f_c = person[i][1];
						idx = i;
					} else if(f_r==person[i][0]) {
						if(f_c>person[i][1]) {
							f_r = person[i][0];
							f_c = person[i][1];
							idx = i;
						}
					}
				}
			}
			
			if(flag) break;
			
			//2. 태운 승객 위치에서 그 승객의 도착 위치까지의 최단거리 구하고 연료 빼주기
			fuel -= d;
			taxiR = f_r; taxiC =f_c;
			int dt = find(person[idx][2], person[idx][3]);
			fuel -= dt;
			
			if(fuel>=0) {
				//연료>=0이면 최단거리x2만큼 연료에 더해주기 
				fuel += dt*2;
				p_cnt--; //손님 다 태울 때까지 123 반복
				taxiR = person[idx][2]; taxiC =person[idx][3];
				a[idx] = true;
			} else {
				//연료<0이면 종료 -1 출력
				fuel = -1;
				break;
			}

		}
		
		//한 명이라도 못 간다면 -1
		if(flag) fuel = -1;
		System.out.println(fuel);
	}

	private static int find(int r, int c) {
		v = new boolean[N+1][N+1];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c, 0));
		v[r][c] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int cr = p.r; int cc = p.c;
			
			if(cr==taxiR && cc==taxiC) {
				return p.dt;
			}
			
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				//범위 방문 벽
				if(nr<=0 || nr>N || nc<=0 || nc>N) continue;
				if(v[nr][nc]) continue;
				if(map[nr][nc]==1) continue;
				
				q.add(new Point(nr, nc, p.dt+1));
				v[nr][nc] = true;
			}
		}
		
		return -1;
	}

	static class Point {
		int r;
		int c;
		int dt;
		
		public Point(int r, int c, int dt) {
			super();
			this.r = r;
			this.c = c;
			this.dt  = dt;
		}
	}
}
