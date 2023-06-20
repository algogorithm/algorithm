package week14_0620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144 {
	//미세먼지 안녕!
	static int R,C,T;
	static int[][] map;
	static int[][] dust;
	//					상 하 좌 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		int up_row = 0; int down_row = 0; 
		
		map = new int[R][C];
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] ==  -1) { //공기청정기 위치
					if(up_row == 0) {
						up_row = r;
					} else {
						down_row = r;
					}
				}
			}
		}
		
		for(int t=0; t<T; t++) {
			dust = new int[R][C];
			//미세먼지 모든 칸에서 확산
			//숫자가 있는 칸(0이 아닌 칸)만 네방향 탐색 후 가능한 곳에 확산됨
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					if(map[r][c] != 0) {
						spread(r,c);
					}
				}
			}
			
			map = new int[R][C];
			//공기청정기 바람 슝
			//위쪽 반시계방향으로 한 칸씩 이동
			//먼저 공기청정기 있는 줄
			for(int c=1; c<C-1; c++) {
				//먼지가 있는 칸이면 슝 -
				if(dust[up_row][c] != 0) {
					map[up_row][c+1] = dust[up_row][c];
				}
			}
			
			for(int r=up_row; r>0; r--) {
				//먼지가 있는 칸이면 슝 -
				if(dust[r][C-1] != 0) {
					map[r-1][C-1] = dust[r][C-1];
				}
			}
			
			for(int c=C-1; c>0; c--) {
				//먼지가 있는 칸이면 슝 -
				if(dust[0][c] != 0) {
					map[0][c-1] = dust[0][c];
				}
			}
			
			for(int r=0; r<up_row-1; r++) {
				//먼지가 있는 칸이면 슝 -
				if(dust[r][0] != 0) {
					map[r+1][0] = dust[r][0];
				}
			}

			//아래쪽 시계방향으로 한 칸씩 이동
			for(int c=1; c<C-1; c++) {
				//먼지가 있는 칸이면 슝 -
				if(dust[down_row][c] != 0) {
					map[down_row][c+1] = dust[down_row][c];
				}
			}
			
			for(int r=down_row; r<R-1; r++) {
				//먼지가 있는 칸이면 슝 -
				if(dust[r][C-1] != 0) {
					map[r+1][C-1] = dust[r][C-1];
				}
			}
			
			for(int c=C-1; c>0; c--) {
				//먼지가 있는 칸이면 슝 -
				if(dust[R-1][c] != 0) {
					map[R-1][c-1] = dust[R-1][c];
				}
			}
			
			for(int r=R-1; r>down_row+1; r--) {
				//먼지가 있는 칸이면 슝 -
				if(dust[r][0] != 0) {
					map[r-1][0] = dust[r][0];
				}
			}
			
			//미세먼지 영향 없는 부분
			for(int r=1; r<R-1; r++) {
				for(int c=1; c<C-1; c++) {
					if(r==up_row || r==down_row) continue;
					
					map[r][c] = dust[r][c];
				}
			}
		}

		//미세먼지 양 계산
		int sum = 0;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				sum+=map[r][c];
			}
		}
		
		System.out.println(sum);
	}

	private static void spread(int r, int c) {
		//확산 가능한 칸의 수
		int cnt = 0; 
		
		//4방탐색
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			//범위 내고 공기청정기 위치가 아니면 미세먼지 확산 가능
			if(check(nr,nc) && map[nr][nc] != -1) {
				cnt++;
				//기존의 값 + 확산된 양
				dust[nr][nc] += map[r][c]/5;
				
			}
		}
		
		//원래 값에서 확산된 양만큼 빼주기
		dust[r][c] += map[r][c] - ((map[r][c]/5)*cnt);
	}

	private static boolean check(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) {
			return true;
		}
		return false;
	}

	private static void print(int[][] map) {
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				System.out.print(map[r][c]+" ");
			}
			System.out.println();
		}
	}

}
