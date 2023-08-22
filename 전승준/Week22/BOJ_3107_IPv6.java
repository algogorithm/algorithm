package Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3107_IPv6 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String IPv6 = br.readLine();
		String[] ipSplit = IPv6.split("::");
		int colons = 0;
		
		for(int i=0; i<ipSplit.length; ++i) {
			for(int j=0; j<ipSplit[i].length(); ++j) {
				if(ipSplit[i].charAt(j) == ':') ++colons;
			}
		}
		
		// :: 가 없을 때
		for(String str : ipSplit[0].split(":")) {
			sb.append(fillZero(str)+":");	
		}
		
		// ::가 있다면			
		if(ipSplit.length == 2) {
			for(int i=0; i<7-colons-1; ++i) {
				sb.append("0000:");
			}
			
			for(String str : ipSplit[1].split(":")) {
				sb.append(fillZero(str)+":");	
			}
		}
		
		while(sb.length() < 40) {
			sb.append("0000:");
		}
		
		System.out.println(sb.deleteCharAt(sb.length()-1));
	}

	private static String fillZero(String input) {
		String tmp = "";
		
		for(int i=input.length(); i<4; ++i) {
			tmp += "0";
		}
		
		return tmp + input;
	}

}
