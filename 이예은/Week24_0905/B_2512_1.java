package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2512_1 {

	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int money[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
		}
		int total = Integer.parseInt(br.readLine());
		int max = 0, sum = 0, n = N;
		
		Arrays.sort(money);
		
		for(int i=0; i<N; i++, n--) {
			if(sum + money[i]*n > total) {
				max = money[i];
				break;
			}
			
			sum += money[i];
		}
		
		while(n > 0) {
			if(sum + max * n <= total)	break;
			max--;
		}
		
		System.out.print(max == 0 ? money[N-1] : max);
	}
}