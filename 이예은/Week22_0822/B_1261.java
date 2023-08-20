package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class B_1261 {
	final static int R[] = {0,1,-1,0}, C[] = {1,0,0,-1};
	static int N, M;
	static char[][] map;
	
	public static int move() {
		Deque<int[]> deque = new ArrayDeque<int[]>();
		boolean visit[][] = new boolean[N][M];
		visit[0][0] = true;
		deque.add(new int[] {0,0,0});
		
		while(!deque.isEmpty()) {
			int spot[] = deque.poll();
			
			if(spot[0] == N-1 && spot[1] == M-1)	return spot[2];
			
			for(int i=0; i<4; i++) {
				int dr = spot[0] + R[i];
				int dc = spot[1] + C[i];
				
				if(dr < 0 || dr >= N || dc < 0 || dc >= M || visit[dr][dc])	continue;
				
				visit[dr][dc] = true;
				if(map[dr][dc] == '0')	deque.addFirst(new int[] {dr, dc, spot[2]});
				else	deque.addLast(new int[] {dr, dc, spot[2]+1});
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		System.out.print(move());
	}
}