package week22_0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3107 {
	//IPv6

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] ipv6 = br.readLine().split(":", -1);
		
		int idx = 0;
		for(int i=0; i<ipv6.length; i++) {
			if(ipv6[i].length() == 0) { //아무것도 없으면 0000:
				//전체 자리수 = 8
				// ::일 경우 연속적으로 넣어줘야 함
				while(8-idx >= ipv6.length - i) {
					//
					sb.append("0000:");
					idx++;
				}
				
				continue;
			}
			
			//4자리수가 아니면 빈자리만큼 0 넣어주고 마지막에 ":" 넣어주기
			if(ipv6[i].length()<4) {
				for(int j=ipv6[i].length(); j<4; j++) {
					sb.append("0");
				}
			}
			sb.append(ipv6[i]).append(":");
			idx++;
		}
		
		//맨뒤 ":" 없애주기
		sb.deleteCharAt(sb.length()-1);
		
		System.out.print(sb);
	}

}
