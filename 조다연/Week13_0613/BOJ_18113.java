package week13_0613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_18113 {
	//그르다 김가놈
	static int N,K,M,answer;
	static List<Integer> gimbap;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//김밥 개수 N, 꼬다리 길이 K, 김밥조각 최소 개수 M
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		gimbap = new ArrayList<Integer>();
		
		int right = 0;		
		for(int i=0; i<N; i++) {
			int len = Integer.parseInt(br.readLine());
			
			//한쪽도 못 자르면 버려
			if(len <= K) continue;
			
			// 2K보다 길면 양쪽 꼬다리 잘라
			// 2K보다 짧으면 한쪽만 잘라
			len = (len<2*K) ? len-K: len-2*K;
			
			//제일 긴 걸루
			right = Math.max(right, len);
			
			if(len != 0) gimbap.add(len);
		}
		
		int left = 1;
		int mid = 0;
		answer = -1;
		
		if(!gimbap.isEmpty()) {
			while(left <= right) {
				mid = (left+right)/2;
				
				int cnt = 0; 
				for(int i=0; i<gimbap.size(); i++) {
					cnt += gimbap.get(i)/mid;
				}
				
				if(cnt>=M) {//최소 개수 맞아
					answer = mid;
					left = mid + 1;
				} else { //최수 개수 안돼 그럼 길이 줄여
					right = mid - 1;
				}
			}
		}
		
		System.out.println(answer);
	}

}
