package Week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13901_로봇 {
	static int[] DR = {-1,1,0,0};
	static int[] DC = {0,0,-1,1};
	static int R, C;
	static int[][] MAP;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		MAP = new int[R][C];
		
		int obstacleCnt = Integer.parseInt(br.readLine());
		for(int i=0; i<obstacleCnt; i++) {
			st = new StringTokenizer(br.readLine());
			MAP[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 9; // 장애물
		}
		st = new StringTokenizer(br.readLine());
		int robotR = Integer.parseInt(st.nextToken());
		int robotC = Integer.parseInt(st.nextToken());
		
		int[] direction = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<direction.length; i++) {
			direction[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		int[] res = bfs(robotR, robotC, direction);
		System.out.println(res[0]+" "+res[1]);
	}
	
	private static int[] bfs(int robotR, int robotC, int[] direction) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {robotR, robotC});
		boolean[][] v = new boolean[R][C];
		v[robotR][robotC] = true;
		int d = 0;
		int[] poll = new int[2];
		
		while(!q.isEmpty()) {
			poll = q.poll();
			MAP[poll[0]][poll[1]] = 1;
			
			for(int i=0; i<4; i++) {
				int nr = poll[0] + DR[direction[d]];
				int nc = poll[1] + DC[direction[d]];
				
				if(nr>=0 && nr<R && nc>=0 && nc<C && MAP[nr][nc] == 0) {
					q.add(new int[] {nr, nc});
					break;
				} else {
					d++;
					d%=4;
				}
			}
			
		}
		return poll;
	}
}
