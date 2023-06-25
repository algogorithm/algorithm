package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_5052 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			String ans = "YES";
			String number[] = new String[N];
			for(int i=0; i<N; i++) {
				number[i] = br.readLine();
			}
			
			Arrays.sort(number);
			
			for(int i=0; i<N-1; i++) {
				if(number[i].length() >= number[i+1].length())	continue;
				
				String str = number[i+1].substring(0, number[i].length());
				if(number[i].equals(str))	{
					ans = "NO";
					break;
				}
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.print(sb);
	}
}