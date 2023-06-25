package Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17086_아기_상어_2 {
	static class Shark {
		int row;
		int col;
		int moves;
		
		public Shark(int row, int col) {
			this.row = row;
			this.col = col;
		}
		public Shark(int row, int col, int moves) {
			this.row = row;
			this.col = col;
			this.moves = moves;
		}
	}
	static int[] dr = {-1,-1,-1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1,-1, 1,-1, 0, 1};
	static int[][] MAP;
	static List<Shark> input;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		MAP = new int[N][M];
		input = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
				input.add(new Shark(i, j));
			}
		}
		
		int result = 0;
		for(int i=0; i<input.size(); i++) {
			result = Math.max(result, bfs(N, M, input.get(i)));			
		}
		
		System.out.println(result);
	}
	private static int bfs(int N, int M, Shark start) {
		Queue<Shark> sharkQueue = new LinkedList<>();
		boolean[][] v = new boolean[N][M];
		
		sharkQueue.offer(start);
		v[start.row][start.col] = true;
		
		while(!sharkQueue.isEmpty()) {
			Shark sh = sharkQueue.poll();
			
			if(MAP[sh.row][sh.col] == 1) {
				return sh.moves;
			}
			
			for(int d=0; d<8; ++d) {
				int nr = sh.row + dr[d];
				int nc = sh.col + dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M && !v[nr][nc]) {
					sharkQueue.offer(new Shark(nr, nc, sh.moves+1));
					v[nr][nc] = true;
				}
			}
		}
		return 0;
	}
}
