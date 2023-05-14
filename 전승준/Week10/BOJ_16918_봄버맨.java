package Week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16918_봄버맨 {
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken()) % 4;
		
		char[][] map = new char[r][c];
		char[][] oMap = new char[r][c];
		
		for(int i=0; i<r; i++) {
			String str = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for(int i=0; i<map.length; i++) {
			Arrays.fill(oMap[i], 'O');
		}
		
		
		if(n == 1) print(map);
		else if(n == 3) print(exploded(map, oMap));
		else print(oMap);
	}

	private static char[][] exploded(char[][] map, char[][] oMap) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == 'O') {
					oMap[i][j] = '.';
					for(int d=0; d<4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						
						if(nr>=0 && nr<map.length && nc>=0 && nc<map[0].length) {
							oMap[nr][nc] = '.';
						}
					}
				}
			}
		}
		return oMap;
	}

	private static void print(char[][] map) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
	}

}
