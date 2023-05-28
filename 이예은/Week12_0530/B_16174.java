package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16174 {
	static int N, map[][];
	static int r[] = {0,1}, c[] = {1,0};
	
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static boolean search() {
		boolean visit[][] = new boolean[N][N];
		Queue<Point> queue = new LinkedList<Point>();
		visit[0][0] = true;
		queue.add(new Point(0, 0));
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			if(map[p.x][p.y] == -1)	return true;
			
			for(int i=0; i<2; i++) {
				int dr = p.x + r[i]*map[p.x][p.y];
				int dc = p.y + c[i]*map[p.x][p.y];
				if(dr >= 0 && dr < N && dc >= 0 && dc < N && !visit[dr][dc]) {
					visit[dr][dc] = true;
					queue.add(new Point(dr, dc));
				}
			}
		}
		
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.print(search() ? "HaruHaru" : "Hing");
	}
}