package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_18405 {
	static final int r[] = {-1,0,1,0}, c[] = {0,1,0,-1};
	
	static class Virus implements Comparable<Virus>{
		int x, y, n, t;
		
		Virus(int x, int y, int n, int t) {
			this.x = x;
			this.y = y;
			this.n = n;
			this.t = t;
		}
		
		@Override
		public int compareTo(Virus v) {
			if(this.t == v.t)	return this.n - v.n;
			return this.t - v.t;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int map[][] = new int[N][N];
		PriorityQueue<Virus> virus = new PriorityQueue<Virus>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0)	virus.add(new Virus(i, j, map[i][j], 0));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()), X = Integer.parseInt(st.nextToken())-1, Y = Integer.parseInt(st.nextToken())-1;;
		
		while(!virus.isEmpty()) {
			Virus v = virus.poll();
			
			if(v.t == S || (v.x == X && v.y == Y))	break;
			
			for(int d=0; d<4; d++) {
				int dr = v.x + r[d];
				int dc = v.y + c[d];
				
				if(dr < 0 || dr >= N || dc < 0 || dc >= N || map[dr][dc] != 0)	continue;
				
				map[dr][dc] = v.n;
				virus.add(new Virus(dr, dc, map[dr][dc], v.t+1));
			}
		}
		
		System.out.print(map[X][Y]);
	}
}