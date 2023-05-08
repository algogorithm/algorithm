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
			int sum = 0, cnt = 0;
			
			
			for(int tgetB=1; tgetB<buildings.length; tgetB++) {
				if(currB == tgetB) {
					continue;
				}
				
				double x = Math.abs(currB - tgetB); // 밑변
				double y = buildings[currB]; // 높이
				double a = x / y; // tangent
				System.out.println("currB: "+currB+" tgetB: "+tgetB+" tangent: "+a);
				double b = y - a*x; //직선의 방정식 y = ax + b;
				
				if(tgetB < currB) {
					for(int i=currB-1; i>=tgetB; i--) {
						if(buildings[i] > (a * x) + b || i == currB-1) cnt++;
						else break;
					}
				}
				else if(currB < tgetB) {
					for(int i=currB; i<tgetB; i++) {
						if(buildings[i] > (a * x) + b || i == currB) cnt++;
						else break;
					}
				}
				
				//System.out.println("currB: "+currB+" tgetB: "+tgetB);
				
				
			}
			System.out.println();
			sum += cnt;
			res = Math.max(res, sum);
		}
		System.out.println(res);
	}

}

/*

		for(int currB=1; currB<buildings.length; currB++) {
			int sum = 0, cnt = 0;
			
			for(int tgetB=1; tgetB<buildings.length; tgetB++) {
				if(currB == tgetB) {
					continue;
				}
				
				double x = tgetB, y=buildings[tgetB];
				double a = Math.abs((buildings[currB] - buildings[tgetB]) / (currB - tgetB));
				double b = y - a*x; //y = ax + b;
				
				for(double i=tgetB-1; i>1; i--) {
					if(i > (y - b) / a) cnt++;
					else break;
				}
				
				for(int i=tgetB+1; i<buildings.length; i++) {
					if(i > (y - b) / a) cnt++;
					else break;
				}
			}
			sum += cnt;
			res = Math.max(res, sum);
		}




 */
