package Week25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18808_스티커_붙이기 {
	static int[][] notebook;
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		notebook = new int[N][M];
		
		for(int order=1; order<=K; ++order) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int[][] paper = new int[R][C];
			
			for(int i=0; i<R; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<C; ++j) {
					paper[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Loop:for(int rot=0; rot<4; ++rot) {
				
				for(int i=0; i<N; ++i) {
					for(int j=0; j<M; ++j) {
						if(checkAndAttachPaper(i, j, paper)) {
							break Loop;
						}
					}
				}
				
				paper = rotate(R, C, paper);
				int tmp = R;
				R = C;
				C = tmp;
			}
		}
		
		int result = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(notebook[i][j] == 1) ++result;
			}
		}
		
		System.out.println(result);
	}
	private static boolean checkAndAttachPaper(int nr, int nc, int[][] paper) {
		boolean isBlank = true;
		
		Loop:for(int r=0; r<paper.length; ++r) {
			for(int c=0; c<paper[0].length; ++c) {
				if(r+nr >= N || c+nc >= M || (paper[r][c] == 1 && notebook[r+nr][c+nc] == 1)) {
					isBlank = false;
					break Loop;
				}
			}
		}
		
		if(isBlank) {
			for(int r=0; r<paper.length; ++r) {
				for(int c=0; c<paper[0].length; ++c) {
					notebook[r+nr][c+nc] += paper[r][c];
				}
			}
			
			return true;
		}
		
		return false;
	}
	private static int[][] rotate(int R, int C, int[][] paper) {
		int[][] tmpPaper = new int[C][R];
		int pi = 0, pj = 0;

		for(int j=R-1; j>=0; --j) {
			for(int i=0; i<C; ++i) {
				tmpPaper[i][j] = paper[pi][pj++];
			}
			pi++;
			pj %= C;
		}
		
		return tmpPaper;
	}
}
