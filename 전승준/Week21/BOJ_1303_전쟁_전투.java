package Week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1303_전쟁_전투 {
	static char[][] MAP;
	static int N, M;
	static int[] drc = {0,-1,0,1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		MAP = new char[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			System.out.println("STR:"+str);
			for(int j=0; j<M; ++j) {
				MAP[i][j] = str.charAt(j);
			}
		}
		
		int answerW = 0, answerB = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				char curCh = MAP[i][j];
				
				if(curCh != 'x') {
					int res = bfs(i, j, curCh);
					
					if(curCh == 'W') answerW += res * res;
					else if(curCh == 'B') answerB += res * res;
				}
			}
		}
		
		System.out.println(answerW + " " + answerB);
	}
	private static int bfs(int sr, int sc, char sCh) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sr, sc});
		MAP[sr][sc] = 'x';
		int count = 1;
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			
			for(int d=0; d<4; ++d) {
				int nr = poll[0] + drc[d];
				int nc = poll[1] + drc[d+1];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M && MAP[nr][nc] == sCh) {
					q.add(new int[] {nr, nc});
					MAP[nr][nc] = 'x';
					++count;
				}
			}
		}
		return count;
	}

}
