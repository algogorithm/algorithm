package Week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2583_영역_구하기 {
	static int[][] map;
	static List<Integer> resultList;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		
		for(int t=0; t<K; t++) {
			st = new StringTokenizer(br.readLine());
			int fy = Integer.parseInt(st.nextToken());
			int fx = Integer.parseInt(st.nextToken());
			int ly = Integer.parseInt(st.nextToken());
			int lx = Integer.parseInt(st.nextToken());
			
			for(int i=fx; i<lx; i++) {
				for(int j=fy; j<ly; j++) {
					map[i][j] = 1;
				}
			}
		}
		
		resultList = new LinkedList<>();
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == 0) {
					bfs(i, j);
				}
			}
		}
		
		Collections.sort(resultList, Comparator.naturalOrder());
		
		System.out.println(resultList.size());
		for(int i=0; i<resultList.size(); i++) {
			System.out.print(resultList.get(i)+" ");
		}
	}
	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		int cnt = 0;
		map[r][c] = 1;
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			
			cnt++;
			
			for(int d=0; d<4; d++) {
				int nr = poll[0] + dr[d];
				int nc = poll[1] + dc[d];
				
				if(nr>=0 && nr<map.length && nc>=0 && nc<map[0].length && map[nr][nc] == 0) {
					q.add(new int[] {nr, nc});
					map[nr][nc] = 1;
				}
			}
		}
		resultList.add(cnt);
	}
}
