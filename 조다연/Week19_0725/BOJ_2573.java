package week19_0725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {
	static int N, M;
	static int[][] arr;
	static int[][] sea;
	//					상 하 좌 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] v;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		sea = new int[N][M];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		
		while(true) {
			//처음에 일단 상하좌우 접해있는 바다 면적만큼 녹여주기
			melt();
			
			//시간 흐름
			time++;
			
			//분리되는 영역 개수 구하기
			int cnt = checkSize();
			
			if(cnt==0) {//빙산이 다 녹을 때까지 분리되지 않으면 0 출력
				System.out.println("0");
				break;
			} else if(cnt>=2) {//두 덩어리 이상으로 분리되는 최초의 시간 출력
				System.out.println(time);
				break;
			}
		}
	}

	//얼음 녹이기
	private static void melt() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				//바다일 땐 지나가
				if(arr[r][c]==0) continue;
				
				//0 아닌 빙산일 때, 상하좌우 탐색하여 바다인 면적개수만큼 빼주기
				int cnt = 0;
				int cr = r; int cc = c;
				for(int d=0; d<4; d++) {
					int nr = cr+dr[d];
					int nc = cc+dc[d];

					if(check(nr, nc) && arr[nr][nc]==0) cnt++;
				}

				sea[r][c] = arr[r][c] - cnt;
				
				//저장된 높이는 0보다 줄어들지 않음
				if(sea[r][c]<0) sea[r][c] = 0;
			}
		}
		copy();
	}
	
	//깊은 복사
	private static void copy() {
		for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                arr[i][j] = sea[i][j];  
            }
        }
	}

	//분리된 덩어리 개수
	private static int checkSize() {
		int cnt = 0;
		v = new boolean[N][M];
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				//바다가 아니고 방문한 적 없는 좌표부터 한덩어리인지 탐색
				if(arr[r][c]>0 && !v[r][c]) {
					cnt++;
					bfs(r,c);
				}
			}
		}
		return cnt;
	}

	//이어져있는지 탐색
	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		
		q.add(new int[] {r,c});
		v[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();

			for(int d=0; d<4; d++) {
				int nr = now[0]+dr[d];
				int nc = now[1]+dc[d];
				
				if(check(nr,nc) && !v[nr][nc] && arr[nr][nc]>0) {
					q.add(new int[] {nr,nc});
					v[nr][nc] = true;
				}
			}	
		}
	}

	//범위 체크
	private static boolean check(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) {
			return true;
		}
		return false;
	}

}
