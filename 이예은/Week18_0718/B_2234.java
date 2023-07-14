package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2234 {
	final static int R[] = {0, -1, 0, 1}, C[] = {-1, 0, 1, 0};
	static int N, M, W[][], G[][];
	static ArrayList<Integer> room;
	
	static int getRoom(int x, int y, int idx) {
		HashSet<Integer> set = new HashSet<Integer>();
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {x, y});
		int cnt = 0, max = 0;
		
		while(!queue.isEmpty()) {
			int r[] = queue.poll();
			cnt++;
			
			for(int d=0; d<4; d++) {
				int dr = r[0] + R[d];
				int dc = r[1] + C[d];
				if(dr < 0 || dr >= N || dc < 0 || dc >= M || G[dr][dc] == idx)	continue;
				
				if((W[r[0]][r[1]] & (1 << d)) == 0)	{
					G[dr][dc] = idx;
					queue.add(new int[] {dr, dc});
				} else if(G[dr][dc] != -1) {
					set.add(G[dr][dc]);
				}
			}
		}
		
		room.add(cnt);
		
		for(int i : set) {
			max = Math.max(max, cnt + room.get(i));
		}
		
		return max;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		W = new int[N][M];
		G = new int[N][M];
		room = new ArrayList<Integer>();
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(G[i], -1);
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans1 = 0, ans2 = 0, ans3 = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(G[i][j] == -1) {
					G[i][j] = ans1;
					ans3 = Math.max(ans3, getRoom(i, j, ans1));
					ans2 = Math.max(ans2, room.get(ans1));
					ans1++;
				}
			}
		}
		
		System.out.print(ans1+"\n"+ans2+"\n"+ans3);
	}
}