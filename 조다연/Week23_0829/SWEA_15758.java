package week23_0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_15758 {
	//무한 문자열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			String answer = "no";
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken(); 
			String t = st.nextToken();
			
			//최소공배수 구하기
			int gcd = gcd(s.length(), t.length());
			int lcm = (s.length()*t.length()) / gcd;
			
			//같은 길이 갖도록 최소공배수만큼 늘리기
			String a = s;
			String b = t;
			
			while(a.length() != lcm) {
				a+=s;
			}
			while(b.length() != lcm) {
				b+=t;
			}
			
			if(a.equals(b)) {
				answer ="yes";
			}
			
			sb.append("#"+tc+" "+answer+"\n");
		}
		
		System.out.println(sb);
	}

	//최대공약수
	private static int gcd(int a, int b) {
		if(b==0) {
			return a;
		} else {
			return gcd(b, a%b);
		}
	}
}
