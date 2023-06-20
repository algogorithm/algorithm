package Week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지_안녕 {
	static final int[] dr = {-1,0,0,1};
	static final int[] dc = {0,-1,1,0};
	static int[][] MAP;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		MAP = new int[R+1][C+1];
		int[] airCleaner = new int[2];
		boolean airCleanerCheck = false;
		
		for(int i=1; i<=R; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=1; j<=C; ++j) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
				
				if(!airCleanerCheck && MAP[i][j] == -1) {
					airCleaner[0] = i;
					airCleaner[1] = i+1;
					
					airCleanerCheck = true;
				}
			}
		}
		
		for(int i=0; i<T; ++i) {
			spreadDust(R, C);
			
			useAirCleaner(R, C, airCleaner);
		}
		
		System.out.println(calcDust(R, C));
	}

	private static void useAirCleaner(int R, int C, int[] acLocation) {
		rotateDust(acLocation[0], 1, 1, C, -1);
		rotateDust(acLocation[1], 1, R, C, 1);
	}

	private static void rotateDust(int startR, int startC, int endR, int endC, int direction) {
		// 위 -> 아래 / 아래 -> 위 (공기청정기로 들어가는 바람)
		if(direction == -1) {
			for(int i=startR-1; i>endR; --i) {
				MAP[i][1] = MAP[i-1][1];
			}			
		} else if(direction == 1) {
			for(int i=startR+1; i<endR; ++i) {
				MAP[i][1] = MAP[i+1][1];
			}
		}

		// 오른쪽 -> 왼쪽
		for(int i=startC; i<endC; ++i) {
			MAP[endR][i] = MAP[endR][i+1];
		}
		
		// 아래 -> 위 / 위 -> 아래 (벽 끝에서 돌음)
		if(direction == -1) {
			for(int i=endR; i<startR; ++i) {
				MAP[i][endC] = MAP[i+1][endC];
			}			
		} else if(direction == 1) {
			for(int i=endR; i>startR; --i) {
				MAP[i][endC] = MAP[i-1][endC];
			}
		}

		// 왼쪽 -> 오른쪽
		for(int i=endC; i>startC; --i) {
			MAP[startR][i] = MAP[startR][i-1];
		}
		
		// 공기청정기에서 나오는 바람은 먼지 X
		MAP[startR][startC+1] = 0;
		
	}

	private static void spreadDust(int R, int C) {
		int[][] tmpMap = new int[R+1][C+1];
		
		for(int i=1; i<=R; ++i) {
			for(int j=1; j<=C; ++j) {
				int spreadCount = 0;
				
				if(MAP[i][j] > 0) {
					for(int d=0; d<4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						
						if(nr>0 && nr<=R && nc>0 && nc<=C && MAP[nr][nc] != -1) {
							tmpMap[nr][nc] += MAP[i][j] / 5;
							++spreadCount;
						}
					}
				}
				MAP[i][j] -= MAP[i][j] / 5 * spreadCount;
			}
		}
		for(int i=1; i<=R; ++i) {
			for(int j=1; j<=C; ++j) {
				MAP[i][j] += tmpMap[i][j];
			}
		}
		
	}
	
	private static int calcDust(int R, int C) {
		int res = 0;
		
		for(int i=1; i<=R; ++i) {
			for(int j=1; j<=C; ++j) {
				if(MAP[i][j] > 0) {
					res += MAP[i][j];
				}
			}
		}
		return res;
	}
}
