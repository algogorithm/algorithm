package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_3649 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = null;
		
		while((str = br.readLine()) != null && str.length() > 0) {
			int X = Integer.parseInt(str) * 10000000;
			int N = Integer.parseInt(br.readLine());
			int lego[] = new int[N];
		
			for(int i=0; i<N; i++) {
				lego[i] = Integer.parseInt(br.readLine());
			}
			
			Arrays.sort(lego);
			int left = 0, right = N-1, l1 = 0, l2 = 0;
			while(left < right) {
				if(lego[right]+lego[left] > X)	right--;
				else if(lego[right]+lego[left] < X)	left++;
				else {
					if(l1 == 0 && l2 == 0) {
						l1 = lego[left];
						l2 = lego[right];
					} else if(l2-l1 < lego[right]-lego[left]) {
						l2 = lego[right];
						l1 = lego[left];
					}
					
					left++;
					right--;
				}
			}
			
			if(l1 == 0 && l2 == 0)	sb.append("danger\n");
			else	sb.append("yes ").append(l1).append(" ").append(l2).append("\n");
		}
		
		System.out.print(sb);
	}
}