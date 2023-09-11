package Week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2512_예산 {
	static int[] needs;
	static int answer, budgetTotal;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		needs = new int[N];
		int requestedBudget = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; ++i) {
			requestedBudget += needs[i] = Integer.parseInt(st.nextToken());
		}
		
		budgetTotal = Integer.parseInt(br.readLine());
		
		if(budgetTotal >= requestedBudget) {
			int max = 0;
			
			for(int n : needs) {
				max = Math.max(max, n);
			}
			System.out.println(max);
			
			return;
		}
		
		binary_search(1, budgetTotal);
		System.out.println(answer);
		
	}
	private static void binary_search(int min, int max) {
		int mid = (min+max) / 2;
		
		if(min == max) {
			return;
		}
		
		if(calculateBudget(mid)) {
			answer = mid;
			binary_search(mid+1, max);
		} else {
			binary_search(min, mid);
		}
	}
	private static boolean calculateBudget(int money) {
		int sum = 0;
		
		for (int n : needs) {
			if(n <= money) sum += n;
			else sum += money;
		}
		
		return sum <= budgetTotal;
	}
}
