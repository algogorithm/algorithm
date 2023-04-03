package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_16987 {
	static int N, ans = 0;
	static class Egg {
		int s, w;
		
		Egg(int s, int w) {
			this.s = s;
			this.w = w;
		}
	}
	
	static void breakEgg(int idx, Egg[] eggs) {
		boolean flag = false;
		
		if(idx == N) {
			int cnt = 0;
			for(int i=0; i<N; i++) {
				if(eggs[i].s <= 0)	cnt++;
			}
			
			ans = Math.max(cnt, ans);
			return;
		}
		
		if(eggs[idx].s > 0) {
			for(int i=0; i<N; i++) {
				if(i != idx && eggs[i].s > 0) {
					eggs[idx].s -= eggs[i].w;
					eggs[i].s -= eggs[idx].w;
					flag = true;
					breakEgg(idx+1, eggs);
					eggs[idx].s += eggs[i].w;
					eggs[i].s += eggs[idx].w;
				}
			}
		}
		
		if(!flag)	breakEgg(idx+1, eggs);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Egg[] eggs = new Egg[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		breakEgg(0, eggs);
		
		System.out.print(ans);
	}
}