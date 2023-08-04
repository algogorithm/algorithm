package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_9252 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char str1[] = br.readLine().toCharArray();
		char str2[] = br.readLine().toCharArray();
		int lcs[][] = new int[str1.length+1][str2.length+1];
		
		for(int i=1; i<=str1.length; i++) {
			for(int j=1; j<=str2.length; j++) {
				if(str1[i-1] == str2[j-1])	lcs[i][j] = lcs[i-1][j-1] + 1;
				else	lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int n = lcs[str1.length][str2.length], i = str1.length, j = str2.length;
		
		while(n > 0) {
			if(lcs[i-1][j] == n)	i--;
			else if(lcs[i][j-1] == n)	j--;
			else {
				sb.append(str1[i-1]);
				n--;
				i--;
				j--;
			}
		}
		
		System.out.println(sb.length());
		System.out.print(sb.reverse());
	}
}