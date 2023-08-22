package week22_0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1261 {
	static int N, M;
	//					�� �� �� ��
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] v;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); //����
		N = Integer.parseInt(st.nextToken()); //����
		
		map = new int[N][M];
		v = new boolean[N][M];
		
		for(int r=0; r<N; r++) {
			String s = br.readLine();
			for(int c=0; c<M; c++) {
				map[r][c] = s.charAt(c) -'0';
			}
		}
		
		int answer = bfs(0,0);
		System.out.println(answer);
	}

	private static int bfs(int r, int c) {
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		pq.add(new Point(r, c, 0));
		v[r][c] = true;
		
		while(!pq.isEmpty()) {
			Point p = pq.poll();
			
			int cr = p.r; int cc = p.c; int cnt = p.cnt;
			
			//���� �� ���� return
			if(cr==N-1 && cc==M-1) {
				return cnt;
			}
			
			//4�� Ž��
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				//�湮, ���� üũ
				if(nr>=0 && nr<N && nc>=0 && nc<M && !v[nr][nc]) {
					v[nr][nc] = true;
					
					if(map[nr][nc]==1) { //���� ��� +1
						pq.add(new Point(nr, nc, cnt+1));
					} else { //�� �ƴ� ���
						pq.add(new Point(nr, nc, cnt));
					}
				}
			}
		}
		
		return 0;
	}
	
	static class Point implements Comparable<Point>{
		int r;
		int c; 
		int cnt;
		
		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return this.cnt - o.cnt;
		}
		
		
	}

}
