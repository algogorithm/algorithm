package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2531 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int sushi[] = new int[N+K];
		int num[] = new int[D+1];
		int cnt = 1;
		
		for(int i=0; i<N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		num[C]++;
		for(int i=0; i<K; i++) {
			sushi[N+i] = sushi[i];
			if(++num[sushi[i]] == 1)	cnt++;
		}
		
		int answer = cnt;
		for(int i=K; i<N+K-1; i++) {
			if(--num[sushi[i-K]] == 0)	cnt--;
			if(++num[sushi[i]] == 1)	cnt++;
			answer = Math.max(answer, cnt);
		}
		
		System.out.print(answer);
	}
}