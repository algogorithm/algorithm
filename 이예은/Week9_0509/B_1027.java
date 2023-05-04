package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1027 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int B[] = new int[N];
		int count[] = new int[N];
		
		Arrays.fill(count, 2);
		count[0]--;	count[N-1]--;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N-1; i++) {
			double max = B[i+1]-B[i];
			for(int j=i+2; j<N; j++) {
				double d  = (double)(B[j]-B[i])/(j-i);
				if(d <= max)	continue;
				max = d;
				count[i]++;
				count[j]++;
			}
		}
		
		int ans = 0;
		for(int i : count) {
			ans = Math.max(ans, i);
		}
		
		System.out.print(ans);
	}
}