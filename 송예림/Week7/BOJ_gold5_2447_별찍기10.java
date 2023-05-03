package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class gold5_2447_별찍기10 {
	
	static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		play(0, 0, N, false);
		
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				sb.append(map[r][c]);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

	private static void play(int r, int c, int n, boolean b) {
		if(b) {
			// map[r][c] = ' '; 하면 맨앞에만 배열에 공백넣고 나머지들은 아무것도 안들어가게됨
			// 컴터에서는 잘 보이겠지만 실제 출력은 틀림..!!
			for (int i = r; i < r+n; i++) {
				for (int j = c; j < c+n; j++) {
					map[i][j] = ' ';
				}
			}
			return;
		}
		if(n==1) {
			map[r][c] = '*';
			return;
		}
		
		/*
		 * n = 27일때, 한 블록의 사이즈는 9
		 * n = 9일때, 한 블록의 사이즈는 3
		 * n = 3일때, 한 블록의 사이즈는 1
		 */
		int size = n / 3;
		int cnt = 0;
		for (int i = r; i < r+n; i+=size) {
			for (int j = c; j < c+n; j+=size) {
				cnt++;
				if(cnt==5) {
					play(i, j, size, true);
				} else {
					play(i, j, size, false);
				}
			}
		}
	}

}