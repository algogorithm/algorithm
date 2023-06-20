package week14_0620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_5052_2 {
	//전화번호 목록

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		for(int tc=0; tc<t; tc++) {
			//전화번호 수
			int n = Integer.parseInt(br.readLine());
			
			String[] list = new String[n];
			for(int i=0; i<n; i++) {
				list[i] = br.readLine();
 			}
			
			Arrays.sort(list);

			boolean flag = false;
			for(int i=0; i<n-1; i++) {
				if(list[i+1].startsWith(list[i])) {
					flag = true;
					break;
				}
			}
			
			System.out.println(flag ? "NO" : "YES");
		}
	}

}
