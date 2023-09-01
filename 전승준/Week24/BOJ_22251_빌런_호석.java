package Week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
-1-
2 3
-4-
5 6
-7-
*/
public class BOJ_22251_빌런_호석 {
	static boolean[][] number_blink = {
			{false,false,false,false,false,false,false},// 0
			{false,false,true,false,false,true,false}, 	// 1
			{true,false,true,true,true,false,true}, 	// 2
			{true,false,true,true,false,true,true}, 	// 3
			{false,true,true,true,false,true,false}, 	// 4
			{true,true,false,true,false,true,true},		// 5
			{true,true,false,true,true,true,true},		// 6
			{true,false,true,false,false,true,false},	// 7
			{true,true,true,true,true,true,true},		// 8
			{true,true,true,true,false,true,true}		// 9
	};
	static int[] digit;
	static int N, K, P, answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		String X = st.nextToken();

		digit = new int[K];
		
		for(int i=0; i<K; ++i) {
			digit[i] = X.charAt(i);
		}
		
		subset(0, new boolean[K]);
		
		System.out.println(answer);
	}
	private static void subset(int start, boolean[] visited) {
		if(start == K) {
			changeNumber(visited);			
			return;
		}
		
		visited[start] = true;
		subset(start+1, visited);
		visited[start] = false;
		subset(start+1, visited);
	}
	private static int changeNumber(boolean[] visited) {
		int[] tmpDigit = new int[K];
		
		for(int i=0; i<K; ++i) {
			
		}
		
		for(int i=0; i<K; ++i) {
			if(visited[i]) {
				for(int j=0; j<7; ++j) {
					
				}				
			} 
		}
		return 0;
	}
	private static boolean verifyFloor(String curFloor) {
		return Integer.parseInt(curFloor) <= N;
	}

}
