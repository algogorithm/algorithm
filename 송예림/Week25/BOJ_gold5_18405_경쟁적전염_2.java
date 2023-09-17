package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class gold5_18405_경쟁적전염_2 {
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int N, K, S, X, Y;
	static int[][] map;
	static PriorityQueue<Virus> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] != 0) {
					pq.offer(new Virus(r, c, map[r][c]));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		while(S > 0) {
			infection();
			S--;
		}
		
		System.out.println(map[X-1][Y-1]);
	}

	private static void infection() {
		PriorityQueue<Virus> tmpPQ = new PriorityQueue<>();
		
		while(!pq.isEmpty()) {
			Virus cur = pq.poll();
			for (int d = 0; d < dc.length; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 0) {
					map[nr][nc] = cur.no;
					tmpPQ.offer(new Virus(nr, nc, cur.no));
				}
			}
		}
		
		pq = new PriorityQueue<>();
		while(!tmpPQ.isEmpty()) {
			pq.offer(tmpPQ.poll());
		}
	}

	static class Virus implements Comparable<Virus> {
		int r, c, no;
		
		public Virus(int r, int c, int no) {
			this.r = r;
			this.c = c;
			this.no = no;
		}
		
		public int compareTo(Virus o) {
			return Integer.compare(this.no, o.no);
		}
	}
}
