package BaekJoon;

import java.util.*;
import java.io.*;

public class B_3190 {
	static int R[] = {0,1,0,-1};
	static int C[] = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M[][] = new int[N][N];
		int D[] = new int[10001];
		
		int K = Integer.parseInt(br.readLine());
		while(K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		
		int L = Integer.parseInt(br.readLine());
		while(L-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D[Integer.parseInt(st.nextToken())] = st.nextToken().equals("D") ? 1 : -1;
		}
		
		int t = 1, d = 0;
		Deque<int[]> snake = new ArrayDeque<int[]>();
		snake.add(new int[] {0,0});
		M[0][0] = 2;
		
		while(true) {
			int hr = snake.peekFirst()[0]+R[d];
			int hc = snake.peekFirst()[1]+C[d];
			
			if(hr < 0 || hr >= N || hc < 0 || hc >= N || M[hr][hc] == 2)	break;
			
			if(M[hr][hc] == 0) {
				int tail[] = snake.pollLast();
				M[tail[0]][tail[1]] = 0;
			}
			
			snake.addFirst(new int[] {hr, hc});
			M[hr][hc] = 2;
			
			if(t <= 10000)	d = (d+D[t++]+4)%4;
		}
		
		System.out.print(t);
	}
}