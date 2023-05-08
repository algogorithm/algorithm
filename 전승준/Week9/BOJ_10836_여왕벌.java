package Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10836_여왕벌 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int days = Integer.parseInt(st.nextToken());
		int[][] space = new int[m][m];
		
		
		
		print(space);
	}

	private static void print(int[][] space) {
		for(int i=0; i<space.length; i++) {
			for(int j=0; j<space[0].length; j++) {
				System.out.print((space[i][j]+1)+" ");
			}
			System.out.println();
		}		
	}
}