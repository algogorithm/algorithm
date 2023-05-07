package Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13305_주유소 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfCity = Integer.parseInt(br.readLine());
		int[] distBetweenCity = new int[numberOfCity - 1];
		int[] priceOfOil = new int[numberOfCity];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < distBetweenCity.length; i++) {
			distBetweenCity[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < priceOfOil.length; i++) {
			priceOfOil[i] = Integer.parseInt(st.nextToken());
		}

		int tmpPrice = priceOfOil[0];
		int distance = 0;
		long answer = 0;
		
		for(int i=1; i<priceOfOil.length; i++) {
			distance += distBetweenCity[i-1];
			
			if(tmpPrice > priceOfOil[i] || i==priceOfOil.length-1) {
				answer += (long)tmpPrice * distance;
				System.out.println("tmpPrice: "+tmpPrice+" distance: "+distance+" tmpPrice*distance: "+tmpPrice*distance+" answer: "+answer);
				tmpPrice = priceOfOil[i];
				distance = 0;
			}
		}

		System.out.println(answer);
	}

}
