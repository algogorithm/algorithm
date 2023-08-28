package Week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_15758_무한_문자열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; ++tc) {
			String[] input = br.readLine().split(" ");
			String s1 = input[0];
			String s2 = input[1];
			int gcd = 0;
			
			if(s1.length() >= s2.length()) gcd = calculateLcm(s1.length(), s2.length());
			else gcd = calculateLcm(s2.length(), s1.length());

			String result = "yes";
			int s1Index = 0, s2Index = 0;

			for (int i = 0; i < gcd; ++i) {
				if (s1Index == s1.length()) s1Index = 0;
				if(s2Index == s2.length()) s2Index = 0;

				if (s1.charAt(s1Index++) != s2.charAt(s2Index++)) {
					result = "no";
					break;
				}
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	private static int calculateLcm(int a, int b) {
		return a * b / calculateGcd(a, b);
	}

	private static int calculateGcd(int a, int b) {
		if(a % b == 0) return b;
		return calculateGcd(b, a % b);
	}
}
