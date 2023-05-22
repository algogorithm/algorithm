package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2583 {
	static int M, N;
	static boolean A[][], visit[][];
	static int r[] = {1,0,-1,0}, c[] = {0,1,0,-1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		A = new boolean[N][M];
		visit = new boolean[N][M];
		ArrayList<Integer> answer = new ArrayList<Integer>();
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int x = x1; x < x2; x++) {
				for(int y = y1; y < y2; y++) {
					A[x][y] = true;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!A[i][j] && !visit[i][j]) {
					visit[i][j] = true;
					answer.add(getArea(i, j));
				}
			}
		}
		
		sb.append(answer.size()).append("\n");
		Collections.sort(answer);
		for(int i : answer) {
			sb.append(i).append(" ");
		}
		System.out.print(sb);
	}
	
	static public int getArea(int x, int y) {
		int cnt = 0;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int p[] = queue.poll();
			cnt++;
			
			for(int d=0; d<4; d++) {
				int dr = p[0] + r[d];
				int dc = p[1] + c[d];
				if(dr >= 0 && dr < N && dc >= 0 && dc < M && !visit[dr][dc] && !A[dr][dc]) {
					visit[dr][dc] = true;
					queue.add(new int[] {dr,dc});
				}
			}
		}		
		return cnt;
	}
}