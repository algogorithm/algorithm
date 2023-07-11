package week17_0711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
	//공유기 설치

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //집
		int C = Integer.parseInt(st.nextToken()); //공유기
		
		int[] house = new int[N];
		for(int i=0; i<N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(house);
		
		int start = 1; //최소거리
		int end = house[N-1] - house[0];//최대거리

		int answer = 0;
		
		while(start <= end) {
			int mid = (start+end)/2;
			int install = house[0];//와이파이 설치한 집
			int cnt = 1; //공유기 개수(맨왼쪽집 설치하고 시작)
			
			for(int i=1; i<N; i++) {
				if(house[i]-install>=mid) {
					//거리차가 기준보다 크거나 같아야 설치 가능
					cnt++;
					install = house[i]; //설치했다면 비교 대상 바꿔주기
				}
			}
			
			if(cnt>=C) {
				//실제 설치될 공유기보다 많이 설치했다면
				//오른쪽으로 이동해 간격을 넓혀
				answer = mid;
				start = mid+1;
			} else {
				//공유기를 c보다 적게 설치했다면
				//왼쪽으로 이동해 간격 줄여
				end = mid-1;
			}
		}
		
		System.out.println(answer);
	}

}
