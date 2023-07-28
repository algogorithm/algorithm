package Week19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10703_유성 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		char[][] map = new char[r][c];
		char[][] resMap = new char[r][c];
		
		for(int i=0; i<r; ++i) {
			String input = br.readLine();
			for(int j=0; j<c; ++j) {
				map[i][j] = input.charAt(j);
				resMap[i][j] = '.';
			}
		}
		
		int distance = Integer.MAX_VALUE;
		
		for(int i=0; i<c; ++i) {
			int meteorMax = 0;
			int landMax = 0;
			
			for(int j=0; j<r; ++j) {
				if(map[j][i] == '#') {
					landMax = j;
					break;
				}
			}
			
			boolean isMeteorExists = false;
			for(int j=r-1; j>=0; --j) {
				if(map[j][i] == 'X') {
					meteorMax = j+1;
					isMeteorExists = true;
					break;
				}
			}
			
			if(!isMeteorExists) continue;
			
			distance = Math.min(distance, landMax-meteorMax);
		}
		
		for(int i=0; i<r; ++i) {
			for(int j=0; j<c; ++j) {
				if(map[i][j] == 'X') resMap[i+distance][j] = 'X';
				else if(map[i][j] == '#') resMap[i][j] = '#';
			}
		}
		
		for (char[] chs : resMap) {
			for (char ch : chs) {
				sb.append(ch);
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
		
	}
}

/*
내 반례

9 7
.....X.
.......
.......
.......
.......
.......
#.#....
#.#....
#######

예상 값 :
.......
.......
.......
.......
.......
.......
#.#....
#.#..X.
#######

결과 값 :
.......
.......
.......
.......
.......
.......
#.#..X.
#.#....
#######

isMeteorExists 없을 떄의 오류

 */
