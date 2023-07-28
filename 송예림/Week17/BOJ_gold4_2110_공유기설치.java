package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold4_2110_공유기설치 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] home = new int[N];
		for (int i = 0; i < home.length; i++) {
			home[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(home);
		
		int start = 1, end = home[N-1] - home[0] + 1;
		int result = 1;
		// 공유기 사이 거리
		while(start < end) {
			int mid = (end + start) / 2;
			
			int cnt = 1; // 첫번째집 무조건 설치
			int cur = home[0];
			for (int i = 1; i < home.length; i++) {
				if(home[i] - cur >= mid) {
					cnt++;
					cur = home[i];
				}
			}
			
			if(cnt < C) { // 설치개수 적으면 거리 좁혀야됨
				end = mid;
				// end = mid - 1;
			} else {
				// 설치개수 많거나 같으면 거리 늘려도됨
				start = mid + 1;
				// start = mid;
			}
		}
		
		System.out.println(start - 1);
	}

}