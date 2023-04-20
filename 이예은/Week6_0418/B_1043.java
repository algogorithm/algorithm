package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_1043 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean person[] = new boolean[N+1];
		boolean visit[] = new boolean[M];
		ArrayList<Integer>[] party = new ArrayList[M];
		
		st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		for(int i=0; i<cnt; i++) {
			int p = Integer.parseInt(st.nextToken());
			person[p] = true;
		}
		
		for(int i=0; i<M; i++) {
			party[i] = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			for(int j=0; j<P; j++) {
				party[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		while(cnt > 0) {
			cnt = 0;
			
			for(int i=0; i<M; i++) {
				if(visit[i])	continue;
				
				boolean flag = false;
				ArrayList<Integer> pty = party[i];
				for(int n : pty) {
					if(person[n]) {
						flag = true;
						break;
					}
				}
				
				if(flag) {
					visit[i] = true;
					for(int n : pty) {
						if(!person[n]) {
							person[n] = true;
							cnt++;
						}
					}
				}
			}
		}
		
		int ans = 0;
		for(int i=0; i<M; i++) {
			if(!visit[i])	ans++;
		}
		
		System.out.print(ans);
	}
}