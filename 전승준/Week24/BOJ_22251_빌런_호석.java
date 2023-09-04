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
			{true,true,true,false,true,true,true},// 0
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
	static String originalFloor;
	static int N, K, P, answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		originalFloor = makeNumber(Integer.parseInt(st.nextToken()));
		System.out.println("orgFloor:"+originalFloor);
		for(int i=1; i<=N; ++i) {
			String floor = makeNumber(i);
			System.out.println("floor: "+floor+" checkFloor 결과:"+checkFloor(floor));
			
			
			if(checkFloor(floor) <= P) ++answer;
		}
		
		System.out.println(answer-1);
	}

	private static int checkFloor(String inputFloor) {
		int cnt = 0;
		
		for(int i=0; i<K; ++i) {
			int orgn = originalFloor.charAt(i) - '0';
			int fake = inputFloor.charAt(i) - '0';
			
			if(orgn != fake) {
				for(int j=0; j<7; ++j) {
					if(number_blink[orgn][j] != number_blink[fake][j]) ++cnt;
				}
			}
		}

		return cnt;
	}

	private static String makeNumber(int num) {
		String numToStr = Integer.toString(num);
		int zeros = K - numToStr.length();
		
		for(int i=0; i<zeros; ++i) {
			numToStr = "0" + numToStr;
		}
		return numToStr;
	}
}