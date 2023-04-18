package Week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3187_양치기_꿍 {
	static char[][] map;
	static boolean[][] v;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[] res = new int[2];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		v = new boolean[r][c];
		
		for(int i=0; i<r; i++) {
			String str = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if((map[i][j] == 'v' || map[i][j] == 'k') && !v[i][j]) {
					findAnimals(i, j);
				}
			}
		}
		
		System.out.println(res[0]+" "+res[1]);
	}
	
	private static void findAnimals(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r, c});
		v[r][c] = true;
		int countOfSheep = 0;
		int countOfWolf = 0;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			if(map[now[0]][now[1]] == 'v') countOfWolf++;
			else if(map[now[0]][now[1]] == 'k') countOfSheep++;
			
			for(int d=0; d<4; d++) {
				int nr = now[0] + dr[d];
				int nc = now[1] + dc[d];
				
				if(nr>=0 && nr<map.length && nc>=0 && nc<map[0].length && map[nr][nc] != '#' && !v[nr][nc]) {
					q.add(new int[] {nr, nc});
					v[nr][nc] = true;
				}
			}
		}
		if(countOfSheep > countOfWolf) res[0] += countOfSheep;
		else res[1] += countOfWolf;
	}
}
