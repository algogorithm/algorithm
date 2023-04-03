package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_1976 {
	static int N, M, C[][], plan[];
	
	public static boolean travel() {
		boolean visit[] = new boolean[N];
		Queue<Integer> queue = new LinkedList<Integer>();
		visit[plan[0]] = true;
		queue.add(plan[0]);
		
		while(!queue.isEmpty()) {
			int c = queue.poll();
			for(int j=0; j<N; j++) {
				if(C[c][j] == 1 && !visit[j]) {
					visit[j] = true;
					queue.add(j);
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			if(!visit[plan[i]])	return false;
		}
		
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		C = new int[N][N];
		plan = new int[M];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				C[i][j] = Integer.parseInt(st.nextToken());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			plan[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		System.out.print(travel() ? "YES" : "NO");
		
	}
}