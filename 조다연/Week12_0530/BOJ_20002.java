package week12_0530;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20002 {
	//사과나무
	
	static int N, Ans;
	static int[][] map;
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		arr = new int[N+1][N+1];
		
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				arr[r][c] = arr[r][c-1]+arr[r-1][c]-arr[r-1][c-1]+map[r][c];
			}
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				System.out.print(arr[r][c] +" ");
			}
			System.out.println();
		}
		
		
		Ans = Integer.MIN_VALUE;
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				int X = Math.min(N-r, N-c);
				for(int k=0; k<=X; k++) {
					int profit = arr[r+k][c+k] 
							- arr[r-1][c+k] - arr[r+k][c-1]
									+arr[r-1][c-1];
					Ans = Math.max(Ans, profit);
				}
			}
		}
		
		System.out.println(Ans);
	}

}
