package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dust {
	int r, c, n;
	Dust(int r, int c, int n) {
		this.r = r;
		this.c = c;
		this.n = n;
	}
}

public class B_17144 {
	final static int r[] = {-1,0,1,0}, c[] = {0,1,0,-1};
	static int R, C, T, M[][], A[];
	static Queue<Dust> queue;
	
	static void findDust() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(M[i][j] > 0)	queue.add(new Dust(i, j, M[i][j]));
			}
		}
	}
	
	static void spreadDust() {
		while(!queue.isEmpty()) {
			Dust dust = queue.poll();
			int d = dust.n/5, cnt = 0;
			
			for(int i=0; i<4; i++) {
				int dr = dust.r + r[i];
				int dc = dust.c + c[i];
				if(dr < 0 || dr >= R || dc < 0 || dc >= C || M[dr][dc] == -1)	continue;
				cnt++;
				M[dr][dc] += d;
			}
			
			M[dust.r][dust.c] -= d*cnt;
		}
	}
	
	static void airUp() {
		for(int i=A[0]-1; i>0; i--) {
			M[i][0] = M[i-1][0];
		}
		
		for(int i=0; i+1<C; i++) {
			M[0][i] = M[0][i+1];
		}
		
		for(int i=0; i<A[0]; i++) {
			M[i][C-1] = M[i+1][C-1];
		}
		
		for(int i=C-1; i>1; i--) {
			M[A[0]][i] = M[A[0]][i-1];
		}
		
		M[A[0]][1] = 0;
	}
	
	static void airDown() {
		for(int i=A[1]+1; i<R-1; i++) {
			M[i][0] = M[i+1][0];
		}
		
		for(int i=0; i+1<C; i++) {
			M[R-1][i] = M[R-1][i+1];
		}
		
		for(int i=R-1; i>A[1]; i--) {
			M[i][C-1] = M[i-1][C-1];
		}
		
		for(int i=C-1; i>1; i--) {
			M[A[1]][i] = M[A[1]][i-1];
		}
		
		M[A[1]][1] = 0;
	}
	
	static int sumDust() {
		int sum = 0;
		while(!queue.isEmpty()) {
			sum += queue.poll().n;
		}
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		M = new int[R][C];
		A = new int[2];
		queue = new LinkedList<Dust>();
		
		for(int i=0, k = 0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				M[i][j] = Integer.parseInt(st.nextToken());
				if(M[i][j] > 0)	queue.add(new Dust(i, j, M[i][j]));
				else if(M[i][j] == -1)	A[k++] = i;
			}
		}
		
		while(T-- > 0) {
			spreadDust();
			airUp();
			airDown();
			findDust();
		}
		
		System.out.print(sumDust());	
	}
}