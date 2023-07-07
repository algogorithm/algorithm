package week16_0704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531 {
	//회전초밥
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
	
		int[] sushi = new int[N+k];
		for(int i=0; i<N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int[] num = new int[d+1]; //숫자체크
		num[c]++; //
		int cnt = 1; //c 초밥 무조건 먹어
		
		//연속해서 먹는 접시 수만큼만 
		for(int i=0; i<k; i++) {
			sushi[N+i] = sushi[i];
			num[sushi[i]]++;
			
			if(num[sushi[i]]==1) cnt++; // 7 9 7 30이면 cnt=3
		}
		
		//이후 k부터 k씩 
		int answer = cnt;
		for(int i=k; i<N+k-1; i++) {
			num[sushi[i-k]]--; //앞 접시 빼
			if(num[sushi[i-k]]==0) cnt--; //가짓수빠져 cnt--
			
			num[sushi[i]]++; //뒷 접시 넣어
			if(num[sushi[i]]==1) cnt++; //새로운 초밥이면 cnt++

			answer = Math.max(answer, cnt);
		}
		
		System.out.println(answer);
	}
}
