import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20058 {
	//마법사 상어와 파이어스톰
	static int N, Q;
	static int[][] map, tmp;
	static int answer1, answer2;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] v;
	static boolean[][] melt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		int n = (int) Math.pow(2, N);
		map = new int[n][n];
		tmp = new int[n][n];
		
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		//남아있는 얼음 합, 가장 큰 덩어릭 차지하는 칸 개수
		answer1 = 0; answer2 = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			int l = (int) Math.pow(2, L);

			//90도 회전
			for(int r=0; r<n; r+=l) {
				for(int c=0; c<n; c+=l) {
					rotaion(r,c,l);
				}
			}
			
			melt = new boolean[n][n];
			//상하좌우로 3개 이상 얼음이 인접해있지 않으면 해당칸 --
			for(int r=0; r<n; r++) {
				for(int c=0; c<n; c++) {
					int cnt = ice(r,c);
					if(cnt<3) melt[r][c] = true;
				}
			}
			
			for(int r=0; r<n; r++) {
				for(int c=0; c<n; c++) {
					if(melt[r][c]) map[r][c]--;
					if(map[r][c]<0) map[r][c] = 0;
				}
			}
		}

		
		//가장 큰 얼음 덩어리
		v = new boolean[n][n];
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				answer1+=map[r][c];
				if(v[r][c] || map[r][c]==0) continue;
				answer2 = Math.max(answer2, bfs(r,c));
			}
		}
		

		System.out.println(answer1);
		System.out.println(answer2);
	}

	private static int bfs(int r, int c) {
		int cnt = 1;
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {r,c});
		v[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			int cr = now[0]; int cc = now[1];
			
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				if(nr<0 || nr>=map.length || nc<0 || nc>=map.length) continue;
				if(v[nr][nc]) continue;
				
				if(map[nr][nc]!=0) {
					q.add(new int[] {nr,nc});
					v[nr][nc] = true;
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	private static int ice(int r, int c) {
		int cnt = 0;
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			//범위
			if(nr<0 || nr>=map.length || nc<0 || nc>=map.length) continue;
			
			if(map[nr][nc]!=0) cnt++;
		}
		
		
		return cnt; 
	}

	private static void rotaion(int r, int c, int l) {
		int x=r; int y=c+l-1;
		for(int i=r; i<r+l; i++) {
			for(int j=c; j<c+l; j++) {
				tmp[x][y] = map[i][j];
				x++;
			}
			y--;
			if(x>r+l-1) x=r;
			if(y<c) y=c+l-1;
		}
		
		for(int i=r; i<r+l; i++) {
			for(int j=c; j<c+l; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}

	private static void print(int[][] map) {
		for(int r=0; r<map.length; r++) {
			for(int c=0; c<map[0].length; c++) {
				System.out.print(map[r][c]+" ");
			}
			System.out.println();
		}
	}

}
