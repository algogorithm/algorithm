package week14_0620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15684 {
	//사다리 조작
	static int N, M, H, ans;
	static int[][] map;
	static boolean isFinish;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //세로
		M = Integer.parseInt(st.nextToken()); //가로
		H = Integer.parseInt(st.nextToken()); //세로선 사이 놓을 수 있는 가로선 개수
		
		
		map= new int[H+1][N+1];
		
		//가로 정보
		if(M!=0) {
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				//b번 세로선과 b+1 세로선을 a번 점선 위치에서 연결
				//0:연결없음, 1:우측 세로선과 연결된다, 2: 좌측 세로선과 연결된다. 
				map[a][b] = 1;
				map[a][b+1] = 2;
			}
			
			//정답이 3보다 큰 값이면 -1이라 그 이후는 무의미
			for(int i=0; i<=3; i++) {
				ans = i;
				dfs(1, 1, 0);
				if(isFinish) break;
			}
			
		} 
		
		System.out.println(isFinish ? ans : -1);
		
	}

	private static void dfs(int r, int c, int n) {
		if(isFinish) return;
		
		if(ans == n) {
			//i번에서 출발해 i번에 도착하는지 확인
			if(check()) isFinish = true;
			return;
		}
		
		for(int i=r; i<= H; i++) {
			for(int j=c; j<N; j++) {
				//가로선 두 개가 연속으로 놓여질 수 없음
				//가로선 추가 전 연결된 가로선이 있는지 확인 필요
				if(map[i][j] == 0 && map[i][j+1] == 0) {
					//가로선 추가
					map[i][j] = 1;
					map[i][j+1] = 2;
					
					dfs(1, 1, n+1);
					
					//추가했던 가로선 제거 (백트래킹)
					map[i][j] = 0; 
					map[i][j+1] = 0; 
				}
			}
		}
	}

	private static boolean check() {
		for(int i=1; i<=N; i++) {
			int nc = i;
			int nr = 1;
			
			while(nr <= H) {
				if(map[nr][nc] == 1) nc++; //우측 이동
				else if(map[nr][nc] == 2) nc--; //좌측 이동
				nr++; //아래로 한 칸 이동
			}
			//i번으로 출발해서 i번으로 도착하지 않는 게 하나라도 있다면
			if(nc != i) return false; 
		}

		return true;
	}
}

