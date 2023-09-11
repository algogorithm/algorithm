package week24_0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2512 {
	//예산

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] city = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			city[i] = Integer.parseInt(st.nextToken());
		}
		
		//총 예산
		int budge = Integer.parseInt(br.readLine());
		
		int sum = 0, answer = 0;
		
		//작은 수부터 넣기 위해 정렬
		Arrays.sort(city);
		
		int cnt = N;
		boolean flag = false;
		for(int i=0; i<N; i++) {
			//제일 작은 수를 개수만큼 곱한 값이 예산을 안 넘으면 
			//해당 수는 무조건 넣을 수 있삼
			int num = sum + city[i]*cnt;
			
			if(num > budge) {
				//예산을 넘게 되면 해당 수가 일단 제일 큰 수가 되고
				//상한액을 구해야 됨
				answer = city[i];
				flag = true;
				break;
			}
			
			sum += city[i];
			cnt--;
		}
		
		while(flag) {
			//제일 큰 수인 값을 줄여나가면서 상한액 찾기
			if(sum + answer*cnt <= budge) break;
			answer--;
		}
		
		//예산 안 넘고 싹 다 들어가면 걍 마지막 것이 큰 수다
		if(!flag) answer = city[N-1];
		System.out.println(answer);
	}

}
