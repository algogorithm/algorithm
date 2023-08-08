package Week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719_빗물 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[] heights = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; ++i) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		
		for(int idx=1; idx<W-1; ++idx) {
			int lMax = 0;
			int rMax = 0;
			
			for(int i=idx-1; i>=0; --i) {
				lMax = Math.max(lMax, heights[i]);
			}
			
			for(int i=idx+1; i<W; ++i) {
				rMax = Math.max(rMax, heights[i]);
			}
			
			int lrMax = Math.min(lMax, rMax);
			
			if(lrMax > heights[idx]) {
				result += lrMax - heights[idx];
			}
		}
		
		System.out.println(result);
	}
}
