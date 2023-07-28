package Week18;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2179_비슷한_단어 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] strs = new String[N];
		String s = "";
		String t = "";
		int tmpLength = 0;
		int maxLength = 0;
		for(int i=0; i<N; ++i) {
			strs[i] = br.readLine();
		}
		
		for (int i=0; i<N; ++i) {
			for(int j=i+1; j<N; ++j) {
				if(strs[i].equals(strs[j])) continue;
				
				int minLength = Math.min(strs[i].length(), strs[j].length());
				tmpLength = 0;
				
				for(int k=0; k<minLength; ++k) {
					if(strs[i].charAt(k) == strs[j].charAt(k)) {
						++tmpLength;
					} else break;
				}
				
				if(maxLength < tmpLength) {
					s = strs[i];
					t = strs[j];
					maxLength = tmpLength;
				}
			}
		}
		System.out.println(s+"\n"+t);
	}
}