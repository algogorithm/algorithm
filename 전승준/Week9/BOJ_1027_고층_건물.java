package Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1027_고층_건물 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] buildings = new int[T+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<buildings.length; i++) {
			buildings[i] = Integer.parseInt(st.nextToken());
		}
		
		int res = 0;
		for(int currB=1; currB<buildings.length; currB++) {
			int lMax = 0, rMax = 0;
			double x, y, a, b;
			
			for(int leftB=currB-1; leftB>=1; leftB--) {
				x = leftB - currB;
				y = buildings[currB];
				a = x / y;
				b = y - a * x;
				int cnt = 0;
				System.out.println("a:"+a+" b:"+b);
				
				for(int i=currB-1; i>=1; i--) {
					if(buildings[i] < (a * x) + b) cnt++;
				}
				lMax = Math.max(lMax, cnt);
			}
			
			
			for(int rightB=currB+1; rightB<T+1; rightB++) {
				x = currB - rightB;
				y = buildings[currB];
				a = x / y;
				b = y - a * x;
				int cnt = 0;
				
				for(int i=currB+1; i<=rightB; i++) {
					if(buildings[i] < (a * x) + b) cnt++;					
				}
				rMax = Math.max(rMax, cnt);
			}

			res = Math.max(res, lMax + rMax);
		}
		
		System.out.println(res);
	}

}