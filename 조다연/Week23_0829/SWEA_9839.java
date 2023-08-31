package week23_0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_9839 {
	//최고의 쌍

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			if(N==1) {
				int a = Integer.parseInt(st.nextToken());
				sb.append("#"+tc+" "+a+"\n");
			} else {
				int[] arr = new int[N];
				for(int i=0; i<N; i++) {
					arr[i] = Integer.parseInt(st.nextToken());
				}
				//쌍이 존재하지 않는 경우는 -1
				int max = -1;
				
				//두 개의 정수를 곱하고 연속하는지 확인하기
				for(int i=0; i<N-1; i++) {
					for(int j=i+1; j<N; j++) {
						int num = arr[i]*arr[j];
						if(check(num)) {
							max = Math.max(max, num);
						}
					}
				}
				
				sb.append("#"+tc+" "+max+"\n");
			}
		}
		
		//레전드 println으로 해서 계속 하나 틀렸음;;
		System.out.print(sb);
	}

	private static boolean check(int n) {
		String s = n+"";
		
		for(int i=0; i<s.length()-1; i++) {
			if(Integer.parseInt(s.substring(i, i+1))+1 
					!= Integer.parseInt(s.substring(i+1, i+2))) {
				return false;
			}
		}
		
		return true;
	}
}
