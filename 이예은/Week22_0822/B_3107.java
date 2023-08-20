package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_3107 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String addr[] = br.readLine().split(":", -1);
		
		for(int i=0, idx = 0; i<addr.length; i++) {
			if(addr[i].length() == 0) {
				while(8 - idx >= addr.length - i) {
					sb.append("0000:");
					idx++;
				}
				continue;
			}
			
			for(int j=4; j>addr[i].length(); j--) {
				sb.append('0');
			}
			sb.append(addr[i]).append(':');
			idx++;
		}
		
		sb.deleteCharAt(sb.length()-1);
		System.out.print(sb);
	}
}