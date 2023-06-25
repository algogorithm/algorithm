package Week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_5052_전화번호_목록 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			int numberOfCall = Integer.parseInt(br.readLine());
			Map<String, Integer> map = new HashMap<>();
			String[] phoneNumbers = new String[numberOfCall];
			boolean result = true;
			
			for(int i=0; i<numberOfCall; ++i) {
				phoneNumbers[i] = br.readLine();
				map.put(phoneNumbers[i], i);
			}

			for(int i=0; i<phoneNumbers.length; ++i) {
				for(int j=1; j<phoneNumbers[i].length(); ++j) {
					if(map.containsKey(phoneNumbers[i].substring(0, j))) {
						result = false;
					}
				}
			}
			
			System.out.println(result ? "YES" : "NO");
		}
		
	}

}
