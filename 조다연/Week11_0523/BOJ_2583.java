package week11_0523;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2583 {
	//영역 구하기
	
	static int M, N, K;
	static int[][] map;
	static boolean[][] v;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		v = new boolean[M][N];
		
		K = Integer.parseInt(st.nextToken());
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			
			//왼쪽 아래 꼭지점
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			//오른쪽 위 꼭지점
			int x2 = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken())-1;

			//채워주기
			fill(y1, x1, y2, x2);
		}
//		print(map);
		
		//분리된 영역 개수 모르니까 list
		int answer = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				//방문 x && 안 그려진 영역
				if(!v[i][j] && map[i][j] == 0) {
					//해당 분리된 영역의 넓이
					int cnt = bfs(i,j);
					list.add(cnt);
					//분리된 영역 개수 ++
					answer++;
				}
			}
		}
		
		//영역 넓이 오름차순
		Collections.sort(list);
		
		System.out.println(answer);
		for(int cnt : list) System.out.print(cnt+" ");
	}

	private static int bfs(int i, int j) {
		Queue<int []> q = new LinkedList<int[]>();
		v[i][j] = true;
		q.add(new int[] {i, j});
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int[] data = q.poll();
			int cr = data[0];
			int cc = data[1];
			
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				if(check(nr,nc) && !v[nr][nc] && map[nr][nc] == 0) {
					v[nr][nc] = true;
					q.add(new int[] {nr, nc});
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	private static boolean check(int nr, int nc) {
		if(nr>=0 && nr<M && nc>=0 && nc<N) {
			return true;
		}
		return false;
	}

	private static void fill(int a, int b, int c, int d) {
		for(int i=a; i<=c; i++) {
			for(int j=b; j<=d; j++) {
				if(map[i][j]!=1) {
					map[i][j] = 1;
					v[i][j] = true;
				}
			}
		}
	}

	private static void print(int[][] map) {
		for(int r=0; r<M; r++) {
			for(int c=0; c<N; c++) {
				System.out.print(map[r][c]+" ");
			}
			System.out.println();
		}
	}

}
