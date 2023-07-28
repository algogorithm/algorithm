package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2573 {
	static int N, M, I[][];
	final static int R[] = {-1,0,1,0}, C[] = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		I = new int[N][M];
		int Y = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				I[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(++Y > 0) {
			int group = getGroup(new boolean[N][M]);
			if(group > 1)	break;
			else if(group == 0) {
				Y = 1;
				break;
			}
		}
		
		System.out.print(Y-1);
	}

	private static int getGroup(boolean visit[][]) {
		int group = 0;
		
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<M-1; j++) {
				if(I[i][j] > 0 && !visit[i][j]) {
					group++;
					visit[i][j] = true;
					melting(i, j, visit);
				}
			}
		}
		
		return group;
	}

	private static void melting(int x, int y, boolean visit[][]) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int i[] = queue.poll();
			int cnt = 0;
			
			for(int d=0; d<4; d++) {
				int dr = i[0] + R[d];
				int dc = i[1] + C[d];
				
				if(dr < 0 || dr >= N || dc < 0 || dc >= M || visit[dr][dc])	continue;
				if(I[dr][dc] < 1)	cnt++;
				else {
					visit[dr][dc] = true;
					queue.add(new int[] {dr, dc});
				}
			}
			
			I[i[0]][i[1]] -= cnt;
		}
	}
}