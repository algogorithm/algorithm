package Week16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531_회전_초밥 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] sushiTable = new int[N*2];
		int[] sushiCheck = new int[d+1];
		int res = 0;
		
		for(int i=0; i<N; ++i) {
			sushiTable[N+i] = sushiTable[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=0; i<k; ++i) {
			++sushiCheck[sushiTable[i]];
		}
		sushiCheck[c] = 2;
		
		int right = k;
		for(int left=0; left<N; ++left) {
			int cnt = 0;
			
			--sushiCheck[sushiTable[left]];
			++sushiCheck[sushiTable[right]];
			
			for(int j=0; j<sushiCheck.length; ++j) {
				if(sushiCheck[j] >= 1) ++cnt;
			}
			
			res = Math.max(res, cnt);
			++right;
		}
		
		System.out.println(res);
	}
}
