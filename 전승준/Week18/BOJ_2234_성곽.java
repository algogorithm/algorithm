package Week18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2234_성곽 {
	static int[][] s_map;
	static boolean[][] s_visited;
	static int[] s_nr = {0, -1, 0, 1};
	static int[] s_nc = {-1, 0, 1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		s_map = new int[m][n];
		
		for(int i=0; i<m; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; ++j) {
				s_map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int numOfRooms = 0;
		for(int i=0; i< m; ++i) {
			for(int j=0; j<n; ++j) {
				if(s_visited[i][j]) {
					bfs(i, j);
				}
			}
		}
	}
	private static void bfs(int r, int c) {
		Queue<int []> q = new LinkedList<>();
		q.add(new int[] {r, c});
		s_visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			int num = s_map[poll[0]][poll[1]];
			
			for(int i=1; i<=8; i*=i*2) {
				if((num & i) > 0) {
					q.add(new int[] {poll[0]});
				}
				
			}			
		}
		
		
	}

}
