package week7_0425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2447 {
	//별 찍기 - 10
	static int N;
	static char[][] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		
		star(0, 0, N, false);
		
		StringBuilder sb = new StringBuilder();
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				sb.append(arr[r][c]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void star(int i, int j, int n, boolean blank) {
		//공백일 경우
		if(blank) {
			for(int r=i; r<i+n; r++) {
				for(int c=j; c<j+n; c++) {
					arr[r][c] = ' ';
				}
			}
			return;
		}
		
		//더이상 쪼갤 수 없는 경우
		if(n == 1) {
			arr[i][j] = '*';
			return;
		}
		
		//N=27 -> 블록 사이즈 9
		//N=9 -> 블록 사이즈 3
		int size = n/3;
		int cnt = 0;
		for(int r=i; r<i+n; r+=size) {
			for(int c=j; c<j+n; c+=size) {
				cnt++;
				if(cnt == 5) { //공백일 경우
					star(r, c, size, true);
				} else {
					star(r, c, size, false);
				}
			}
		}
	}
}
