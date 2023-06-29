package week15_0627;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253 {
	//좋다

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0; 
		
		Arrays.sort(arr);
		
		//N이 2 이하면 좋은 수는 0개임
		if(N>2) {
			for(int i=0; i<N; i++) {
				int left = 0; int right = N-1;
				int num = arr[i];
				
				while(left < right) {
					//자기 자신은 하면 안 되기 때무네 다음으로 넘어가
					if(left == i) {
						left++; continue;
					}
					if(right == i) {
						right--; continue;
					}
					
					
					if(arr[left]+arr[right] == num) {
						//더한 값이 수와 같다면 좋다, 찾으면 그만
						answer++; break;
					}
					//더한 값이 수보다 작다면 left++
					else if(arr[left]+arr[right] < num) left++;
					//더한 값이 수보다 크다면 right--
					else right--;
				}
			}
		}
		
		System.out.println(answer);
		
	}

}
