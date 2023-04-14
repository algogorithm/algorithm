package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_3187 {
	static int R, C, K, V;
	static int r[] = {0,0,1,-1}, c[] = {1,-1,0,0};
	static char map[][];
	static boolean visit[][];
	
	static void bfs(int x, int y) {
		int k = map[x][y] == 'k' ? 1 : 0;
		int v = map[x][y] == 'v' ? 1 : 0;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int s[] = queue.poll();
			for(int d=0; d<4; d++) {
				int dr = s[0] + r[d];
				int dc = s[1] + c[d];
				if(dr >= 0 && dr < R && dc >= 0 && dc < C && !visit[dr][dc] && map[dr][dc] != '#') {
					visit[dr][dc] = true;
					if(map[dr][dc] == 'k')	k++;
					else if(map[dr][dc] == 'v')	v++;
					queue.offer(new int[] {dr, dc});
				}
			}
		}
		
		if(v >= k)	V += v;
		else	K += k;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];
		K = 0; V = 0;
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray(); 
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(!visit[i][j] && map[i][j] != '#' && map[i][j] != '.') {
					visit[i][j] = true;
					bfs(i, j);
				}
			}
		}
		
		System.out.print(K+" "+V);
	}
}