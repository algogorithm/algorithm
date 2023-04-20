package week6_0418;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14712 {
	//³Û¸ð³Û¸ð(Easy)
	static int N,M;
	static boolean map[][];
	static int answer;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		
		dfs(0,0);
		
		System.out.println(answer);
	}

	private static void dfs(int r, int c) {
		if(r==N) {
			//¹èÄ¡µÈ ³Û¸ð 2x2 µÇ´ÂÁö È®ÀÎ
			for(int i=0; i<=N-2; i++) {
				for(int j=0; j<=M-2; j++) {
					if(map[i][j] && map[i+1][j] 
							&& map[i][j+1] && map[i+1][j+1]) 
						return;
				}
			}
			
			//³Û¸ð 2x2 ¾ÈµÇ¸é ++
			answer++;
			return;
		}

		//ÁÂÇ¥ ÀÌµ¿
		int nc = (c+1 == M) ? 0 : c+1;
		int nr = (nc == 0) ? r+1 : r;
		
		//ÇØ´ç Ä­¿¡ ³Û¸ð ÀÖ¾î
		map[r][c] = true;
		dfs(nr,nc);
		
		
		//³Û¸ð ¾ø¾î
		map[r][c] = false;
		dfs(nr,nc);
	}

}
