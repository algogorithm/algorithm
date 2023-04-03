package Week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
	static int[][] MAP;
	static int MAX;
	static int[][][] TETROMINO = {
								{{0,1}, {0,2}, {0,3}}, // 파랑 ㅡ
								{{1,0}, {2,0}, {3,0}}, // 파랑 ㅣ
								{{0,1}, {1,0}, {1,1}}, // 노랑 ㅁ
								
								{{1,0}, {2,0}, {2,1}}, // 주황
								{{0,1}, {0,2}, {1,0}}, // 주황
								{{0,1}, {1,1}, {2,1}}, // 주황
								{{1,0}, {1,-1}, {1,-2}}, // 주황
								{{1,0}, {2,0}, {2,-1}}, // 주황
								{{1,0}, {1,1}, {1,2}}, // 주황
								{{1,0}, {2,0}, {0,1}}, // 주황
								{{0,1}, {0,2}, {1,2}}, // 주황
								
								{{1,0}, {1,1}, {2,1}}, // 초록
								{{0,1}, {1,0}, {1,-1}}, // 초록
								{{0,1}, {1,1}, {1,2}}, // 초록
								{{1,0}, {1,-1}, {2,-1}}, // 초록
								
								{{0,1}, {0,2}, {1,1}}, // 보라 ㅜ
								{{0,1}, {-1,1}, {1,1}}, // 보라 ㅓ
								{{1,0}, {1,-1}, {1,1}}, // 보라 ㅗ
								{{0,-1}, {-1,-1}, {1,-1}} // 보라 ㅏ
								};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		MAP = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {				
				calculator(i, j);
			}
		}
		
		System.out.println(MAX);
	}
	private static void calculator(int r, int c) {
		
		for(int i=0; i<TETROMINO.length; i++) {
			boolean check = true;
			int sum = MAP[r][c];
			for(int j=0; j<TETROMINO[0].length; j++) {
				int nr = r + TETROMINO[i][j][0];
				int nc = c + TETROMINO[i][j][1];
				
				if(nr>=0 && nr<MAP.length && nc>=0 && nc<MAP[0].length) {
					sum += MAP[nr][nc];
				} else {
					check = false;
					break;
				}
				
			}
			if(check) {
				MAX = Math.max(MAX, sum);
			}
		}
		
	}

}

