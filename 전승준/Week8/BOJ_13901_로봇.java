package Week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13901_로봇 {
	static int K, R, C, curX, curY, dirIdx;
	static int[][] map;
	static boolean[][] visited;
	static int[] dir = new int[4];
	//상 하 좌 우 
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C];
		K = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < K ; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 2;
		}
		st = new StringTokenizer(br.readLine());
		curX = Integer.parseInt(st.nextToken());
		curY = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i < 4; i++) {
			dir[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		bfs();
		System.out.println(curX+" "+curY);
	}

	private static void bfs() {
		Queue<int []> queue = new LinkedList<int[]>();
		visited[curX][curY] = true;
		queue.offer(new int[] {curX, curY});
		
		while(! queue.isEmpty()) {
			int[] cur = queue.poll();
			for(int i = 0 ; i < 4; i++) {
				int d = dir[(dirIdx + i) % 4];
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				
				if(0 > nx || nx >= R || 0 > ny || ny >= C || visited[nx][ny] == true || map[nx][ny] == 2) continue;
				
				visited[nx][ny] = true;
				queue.offer(new int[] {nx, ny});
				dirIdx = (dirIdx + i) % 4;
				curX = nx;
				curY = ny;
				break;
			}
		}
	}
}
