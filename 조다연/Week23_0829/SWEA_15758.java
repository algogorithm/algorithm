package week23_0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_15758 {
	//���� ���ڿ�

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			String answer = "no";
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken(); 
			String t = st.nextToken();
			
			//�ּҰ���� ���ϱ�
			int gcd = gcd(s.length(), t.length());
			int lcm = (s.length()*t.length()) / gcd;
			
			//���� ���� ������ �ּҰ������ŭ �ø���
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

	//�ִ�����
	private static int gcd(int a, int b) {
		if(b==0) {
			return a;
		} else {
			return gcd(b, a%b);
		}
	}
}
