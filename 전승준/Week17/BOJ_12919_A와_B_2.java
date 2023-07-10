package Week17;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_12919_A와_B_2 {
	static String FIND;
	static boolean res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		FIND = br.readLine();
		String target = br.readLine();
		
		deleteString(target);
		
		if(res) System.out.println(1);
		else System.out.println(0);
	}
	
	private static void deleteString(String target) {
		if(target.equals(FIND)) {
			res = true;
			return;
		}
		
		if(target.equals("")) return;
		
		
		char targetFirst = target.charAt(0);
		char targetLast = target.charAt(target.length()-1);
		
		if(targetFirst == 'B') {
			deleteString(reverse(target).substring(0, target.length()-1));
		}
		if(targetLast == 'A') {
			deleteString(target.substring(0, target.length()-1));
		}
	}

	private static String reverse(String target) {
		String tmp = "";
		
		for(int i=target.length()-1; i>=0; --i) {
			tmp += target.charAt(i);
		}
		
		return tmp;
	}

}
