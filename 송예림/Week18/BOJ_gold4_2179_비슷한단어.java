package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold4_2179_비슷한단어 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(br.readLine());
		}
		
		int max = 0;
		String result1 = "", result2 = "";
		for (int i = 0; i < list.size()-1; i++) {
			String a = list.get(i);
			for (int j = i+1; j < list.size(); j++) {
				String b = list.get(j);
				int tmp = 0;
				for (int k = 0; k < Math.min(a.length(), b.length()); k++) {
					if(a.charAt(k) == b.charAt(k)) {
						tmp++;
					} else {
						break;
					}
				}
				if(max < tmp) {
					result1 = a;
					result2 = b;
					max = tmp;
				}
			}
		}
		System.out.println(result1 + "\n" + result2);
	}
}
