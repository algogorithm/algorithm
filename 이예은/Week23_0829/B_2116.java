package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2116 {
	static final int dice[] = {5, 3, 4, 1, 2, 0};
	static int N, D[][], num[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		D = new int[N][6];
		num = new int[N][6];
		int ans = 0;
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<6; j++) {
				int n = Integer.parseInt(st.nextToken()) - 1;
				D[i][j] = n;
				num[i][n] = j; 
			}
		}
		
		for(int i=0; i<6; i++) {
			int n = i, sum = 0;
			
			for(int j=0; j<N; j++) {
				n = num[j][n];
				
				if(D[j][n] != 5 && D[j][dice[n]] != 5)	sum += 6;
				else if(D[j][n] != 4 && D[j][dice[n]] != 4)	sum += 5;
				else	sum += 4;

				n = D[j][dice[n]];
			}
			
			ans = Math.max(ans, sum);
		}
		
		System.out.print(ans);
	}
}