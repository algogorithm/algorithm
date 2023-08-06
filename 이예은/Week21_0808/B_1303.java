package BaekJoon;

import java.io.*;
import java.util.*;

public class B_1303 {
	static final int R[] = {-1,0,1,0}, C[] = {0,1,0,-1};
	static int N, M;
	static boolean visit[][];
	static char map[][];
	
	static int count(int x, int y) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {x, y});
		visit[x][y] = true;
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			int p[] = queue.poll();
			cnt++;
			
			for(int d=0; d<4; d++) {
				int dr = p[0] + R[d];
				int dc = p[1] + C[d];
				if(dr < 0 || dr >= N || dc < 0 || dc >= M || visit[dr][dc] || map[dr][dc] != map[x][y])	continue;
				
				visit[dr][dc] = true;
				queue.add(new int[] {dr, dc});
			}
		}
		
		return cnt*cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M];
		int S1 = 0, S2 = 0;
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visit[i][j])	continue;
				
				if(map[i][j] == 'W')	S1 += count(i, j);
				else	S2 += count(i, j);
			}
		}
		
		System.out.print(S1+" "+S2);
	}
}