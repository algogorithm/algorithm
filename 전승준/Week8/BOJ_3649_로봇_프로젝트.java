package Week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_3649_로봇_프로젝트 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int holeSize = Integer.parseInt(br.readLine()) * 10000000; // cm -> nm
		
		int numOfLego = Integer.parseInt(br.readLine());
		int[] legos = new int[numOfLego];
		for(int i=0; i<legos.length; i++) {
			legos[i] = Integer.parseInt(br.readLine());
		}
		
		

	}

}
