package Week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253_좋다 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; ++i) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numbers);
		
		int cnt = 0;
		for(int i=0; i<N; ++i) {
			int l = 0, r = N-1;
			
			while(l < r) {
				int sum = numbers[l] + numbers[r];
				
				if(sum < numbers[i]) ++l; // 두 수의 합보다 찾는 수가 클 때
				else if(sum > numbers[i]) --r; // 두 수의 합보다 찾는 수가 작을 때
				else {		
					if(l == i) ++l;
					else if(r == i) --r;
					else { // 두 수가 같을 때와 각 숫자가 찾는 숫자가 아닐 때
						++cnt;
						break;
					}
				}
			}
		}
		
		System.out.println(cnt);
			
	}

}
