package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B_14497 {
	static final int R[] = {-1,0,1,0}, C[] = {0,1,0,-1};
	static int N, M, x1, y1, x2, y2;
	static char room[][];
	
	static int jump() {
		Deque<int[]> deque = new LinkedList<int[]>();
		boolean visit[][] = new boolean[N][M];
		visit[x1][y1] = true;
		deque.add(new int[] {x1, y1, 0});
		
		while(!deque.isEmpty()) {
			int f[] = deque.poll();
			
			if(f[0] == x2 && f[1] == y2)	return f[2];
			
			for(int i=0; i<4; i++) {
				int dr = f[0] + R[i];
				int dc = f[1] + C[i];
				if(dr < 0 || dr >= N || dc < 0 || dc >= M || visit[dr][dc])	continue;
				
				visit[dr][dc] = true;
				if(room[dr][dc] == '0') deque.addFirst(new int[] {dr, dc, f[2]});
				else	deque.addLast(new int[] {dr, dc, f[2]+1});
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new char[N][M];
		
		st = new StringTokenizer(br.readLine());
		x1 = Integer.parseInt(st.nextToken())-1;
		y1 = Integer.parseInt(st.nextToken())-1;
		x2 = Integer.parseInt(st.nextToken())-1;
		y2 = Integer.parseInt(st.nextToken())-1;
		
		for(int i=0; i<N; i++) {
			room[i] = br.readLine().toCharArray();
		}
		
		System.out.print(jump());
	}
}