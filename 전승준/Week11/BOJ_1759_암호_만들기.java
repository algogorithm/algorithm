package Week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1759_암호_만들기 {
	static List<String> strList;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[] alphabets = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			alphabets[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alphabets);
		strList = new LinkedList<>();
		
		combination(0, 0, new char[L], alphabets);
		
		System.out.println(sb);
		
	}
	private static void combination(int cnt, int idx, char[] result, char[] alphabets) {
		if(cnt == result.length) {
			String str = "";
			int jaeum = 0, moeum = 0;
			
			for(int i=0; i<result.length; i++) {
				switch(result[i]) {
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
					moeum++;
					break;
				default:
					jaeum++;
				}
				str += result[i];
			}

			if(jaeum >= 2 && moeum >= 1) {
				sb.append(str+"\n");				
			}
			return;
		}
		
		for(int i=idx; i<alphabets.length; i++) {
			result[cnt] = alphabets[i];
			combination(cnt+1, i+1, result, alphabets);
		}
	}

}
