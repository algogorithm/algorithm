package Week25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19238_스타트_택시 {
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int numberOfPeople = Integer.parseInt(st.nextToken());
		int gas = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		
		for(int i=1; i<N+1; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int[] driver = new int[2];
		driver[0] = Integer.parseInt(st.nextToken());
		driver[1] = Integer.parseInt(st.nextToken());
		
	}
}
