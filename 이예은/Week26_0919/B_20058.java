package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_20058 {
	static final int r[] = {-1, 0, 1, 0}, c[] = {0,1,0,-1};
	static int N, Q, L, sum, A[][];
	static Queue<int[]> queue;
	static boolean visit[][];
	
	static void divide() {
		for(int i=0; i<N; i+=L) {
			for(int j=0; j<N; j+=L) {
				rotate(i, j);
			}
		}
	}
	
	static void rotate(int x, int y) {
		int ice[][] = new int[L][L];
		
		for(int i=0; i<L; i++) {
			for(int j=0; j<L; j++) {
				ice[i][j] = A[x+L-j-1][y+i];
			}
		}
		
		for(int i=0; i<L; i++) {
			for(int j=0; j<L; j++) {
				A[i+x][j+y] = ice[i][j];
			}
		}
	}
	
	static void melt() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int cnt = 0;
				
				for(int d=0; d<4; d++) {
					int dr = i + r[d];
					int dc = j + c[d];
					
					if(dr < 0 || dr >= N || dc < 0 || dc >= N || A[dr][dc] < 1)	cnt++;
				}
				
				if(cnt > 1)	queue.add(new int[] {i, j});
			}
		}
		
		while(!queue.isEmpty()) {
			int ice[] = queue.poll();
			A[ice[0]][ice[1]]--;
		}
	}
	
	static int getIce(int x, int y) {
		int cnt = 0;
		queue.add(new int[] {x, y, A[x][y]});
		visit[x][y] = true;
		
		while(!queue.isEmpty()) {
			int ice[] = queue.poll();
			cnt++;
			sum += ice[2];
			
			for(int d=0; d<4; d++) {
				int dr = ice[0] + r[d];
				int dc = ice[1] + c[d];
				
				if(dr < 0 || dr >= N || dc < 0 || dc >= N || visit[dr][dc] || A[dr][dc] < 1)	continue;
				
				visit[dr][dc] = true;
				queue.add(new int[]{dr, dc, A[dr][dc]});
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
		Q = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		queue = new LinkedList<int[]>();
		visit = new boolean[N][N];
		sum = 0;
		int cnt = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int q=0; q<Q; q++) {
			L = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
			if(L != 1)	divide();
			melt();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visit[i][j] && A[i][j] > 0)	cnt = Math.max(cnt, getIce(i, j));
			}
		}
		
		System.out.println(sum+"\n"+cnt);
	}
}