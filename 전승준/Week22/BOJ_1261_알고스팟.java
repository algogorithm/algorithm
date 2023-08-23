package Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1261_알고스팟 {
	static class Room implements Comparable<Room> {
		int row;
		int col;
		int crashs;
		
		@Override
		public int compareTo(Room o) {
			return this.crashs - o.crashs;
		}

		public Room(int row, int col, int crashs) {
			this.row = row;
			this.col = col;
			this.crashs = crashs;
		}
	}
	static int N, M;
	static int[] drc = {0,1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; ++i) {
			String str = br.readLine();
			for(int j=0; j<M; ++j) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		int result = bfs(map, 0, 0);

		System.out.println(result);
	}

	private static int bfs(int[][] map, int cr, int cc) {
		PriorityQueue<Room> queue = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new Room(cr, cc, 0));
		visited[cr][cc] = true;
		
		while(!queue.isEmpty()) {
			Room poll = queue.poll();
			
			if(poll.row == N-1 && poll.col == M-1) return poll.crashs;
			
			for(int d=0; d<4; ++d) {
				int nr = poll.row + drc[d];
				int nc = poll.col + drc[d+1];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.offer(new Room(nr, nc, poll.crashs+map[nr][nc]));
				}
			}
		}
		
		return 0;
	}

}
