package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_1976_1 {
	static int N, M, city[];
	static boolean E[][], visit[];
	
	static void travel() {
		Queue<Integer> queue = new LinkedList<Integer>();
		visit[city[0]] = true;
		queue.add(city[0]);

		while(!queue.isEmpty()) {
			int n = queue.poll();
			for(int j=0; j<N; j++) {
				if(visit[j] || !E[n][j])	continue;
				
				visit[j] = true;
				queue.add(j);
			}
		}
	}
	
	static boolean possible() {
		for(int c : city) {
			if(!visit[c])	return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		city = new int[M];
		E = new boolean[N][N];
		visit = new boolean[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1)	E[i][j] = true;
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			city[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		travel();
		
		System.out.print(possible() ? "YES" : "NO");
	}
}