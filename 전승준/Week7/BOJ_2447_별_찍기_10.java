package Week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2447_별_찍기_10 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());

		for(int i=0; i<input; i++) {
			for(int j=0; j<input; j++) {
				printStars(i, j, input);
			}
			System.out.println();
		}

	}
	private static void printStars(int i, int j, int num) {
		if((i/num)%3==1 && (j/num)%3==1) System.out.print(" ");
		else {
			if(num / 3 == 0) System.out.print("*");
			else printStars(i, j, num/3);
		}
		
	}

}
