package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_16918 {
	static int r[] = {0,1,-1,0};
	static int c[] = {1,0,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken())-1;
		int bomb[][] = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				if(str.charAt(j) == 'O')	bomb[i][j] = 2;
			}
		}
		
		while(N-- > 0) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(bomb[i][j] == 0)	bomb[i][j] = 3;
					bomb[i][j]--;
				}
			}
			
			if(--N < 0)	break;
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(bomb[i][j] == 1) {
						bomb[i][j] = 0;
						for (int d = 0; d < 4; d++) {
							int dr = i + r[d];
							int dc = j + c[d];
							if(dr >= 0 && dr < R && dc >= 0 && dc < C && bomb[dr][dc] != 1)	bomb[dr][dc] = 0;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(bomb[i][j] == 0) sb.append('.');
				else	sb.append('O');
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
		
	}
}