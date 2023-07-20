package Week19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_빙산 {
	static class Iceberg {
		int r;
		int c;
		int height;
		
		public Iceberg(int r, int c, int height) {
			this.r = r;
			this.c = c;
			this.height = height;
		}
	}
	static int[] drc = {0,-1,0,1,0};
	static int[][] MAP;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		MAP = new int[N][M];
		
		List<Iceberg> icebergList = new ArrayList<>();
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; ++j) {
				int input = Integer.parseInt(st.nextToken());
				
				if(input > 0) icebergList.add(new Iceberg(i, j, input));
				MAP[i][j] = input;
			}
		}
		
		int year = 0;
		
		while(!icebergList.isEmpty()) {
			++year;
			
			for(int i=icebergList.size()-1; i>=0; --i) {
				Iceberg curIce = icebergList.get(i);
				int cnt = 0;
				
				for(int d=0; d<4; ++d) {
					int nr = curIce.r + drc[d];
					int nc = curIce.c + drc[d+1];
					
					if(nr>=0 && nr<N && nc>=0 && nc<M && MAP[nr][nc] == 0) {
						++cnt;
					}
				}
				
				icebergList.get(i).height -= cnt;
			}
			
			for(int i=icebergList.size()-1; i>=0; --i) {
				Iceberg curIce = icebergList.get(i);
				if(curIce.height <= 0) {
					MAP[curIce.r][curIce.c] = 0;
					icebergList.remove(i);
				}
			}
			
			if(icebergList.isEmpty()) {
				System.out.println(0);
				return;
			}
			else if(bfs(icebergList.get(0), N, M) != icebergList.size()) {
				System.out.println(year);
				return;
			}
		}
		System.out.println(0);
	}
	private static int bfs(Iceberg curIce, int N, int M) {
		Queue<Iceberg> q = new LinkedList<>();
		boolean[][] v = new boolean[N][M];
		q.add(curIce);
		v[curIce.r][curIce.c] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Iceberg poll = q.poll();
			
			for(int d=0; d<4; ++d) {
				int nr = poll.r + drc[d];
				int nc = poll.c + drc[d+1];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M && !v[nr][nc] && MAP[nr][nc] > 0) {
					q.add(new Iceberg(nr, nc, MAP[nr][nc]));
					v[nr][nc] = true;
					++cnt;
				}
			}
		}
		return cnt;
	}
}