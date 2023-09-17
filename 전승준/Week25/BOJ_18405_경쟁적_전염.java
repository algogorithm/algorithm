package Week25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_18405_경쟁적_전염 {
	static class Virus implements Comparable<Virus> {
		int r;
		int c;
		int num;
		int sec;
		
		public Virus(int r, int c, int num, int sec) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.sec = sec;
		}
		
		@Override
		public int compareTo(Virus o) {
			if(this.sec == o.sec) return this.num - o.num;
			return this.sec - o.sec;
		}

		@Override
		public String toString() {
			return "Virus [r=" + r + ", c=" + c + ", num=" + num + ", sec=" + sec + "]";
		}
	}
	static int[] drc = {0,-1,0,1,0};
	static PriorityQueue<Virus> pq;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; ++j) {
				int num = Integer.parseInt(st.nextToken());
				
				if(num != 0) {
					pq.add(new Virus(i, j, num, 0));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int seconds = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		System.out.println(secondsAfter(x, y, seconds, N));
	}
	private static int secondsAfter(int rx, int ry, int seconds, int N) {
		boolean[][] visited = new boolean[N][N];
		
		for (Virus vs : pq) {
			visited[vs.r][vs.c] = true;
		}

		while(!pq.isEmpty()) {
			Virus v = pq.poll();
			
			if(v.sec == seconds+1) break;
			
			if(v.r == rx-1 && v.c == ry-1) {
				return v.num;
			}
			
			for(int d=0; d<4; ++d) {
				int nr = v.r + drc[d];
				int nc = v.c + drc[d+1];
				
				if(nr>=0 && nr<N && nc>=0 && nc<N && !visited[nr][nc]) {
					pq.add(new Virus(nr, nc, v.num, v.sec+1));
					visited[nr][nc] = true;
				}
			}
			

		}
		
		return 0;
	}
}
