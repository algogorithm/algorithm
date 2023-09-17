package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2310 {
	static int N, money[], cost[];
	static ArrayList<Integer> room[];
	
	static boolean move(int n, int sum) {
		if(n == N-1)	return true;
		
		for(int idx : room[n]) {
			int next = sum;
			
			if(money[idx] < 0)	next += money[idx];
			else if(next < money[idx])	next = money[idx];
			
			if(cost[idx] < next) {
				cost[idx] = next;
				if(move(idx, next))	return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0)	break;
			money = new int[N];
			cost = new int[N];
			room = new ArrayList[N];
			Arrays.fill(cost, -1);
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				room[i] = new ArrayList<Integer>();
				
				if(st.nextToken().equals("T"))	money[i] = -Integer.parseInt(st.nextToken());
				else	money[i] = Integer.parseInt(st.nextToken());
				
				while(true) {
					int n = Integer.parseInt(st.nextToken());
					if(n == 0)	break;
					room[i].add(n-1);
				}
			}
			
			sb.append(money[0] >= 0 && move(0, money[0]) ? "Yes\n" : "No\n");
		}
		
		System.out.print(sb);
	}
}