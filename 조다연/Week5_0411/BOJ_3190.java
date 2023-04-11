package week5_0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_3190 {
	//3190. 뱀
	static int board[][];
	static int N, K, L;
	static int dir[][];
	//				      우    하      좌      상
	static int dr[] = {0, 1,  0, -1};
	static int dc[] = {1, 0, -1,  0};
	static int index = 0; //오른쪽 방향으로 시작
	static List<int[]> snake;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//보드 크기
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		
		//사과 개수
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			//사과 위치 (행,열)
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			board[r][c] = 2;
		}
		
		//뱀의 방향 변환 횟수 
		L = Integer.parseInt(br.readLine());
		dir = new int[L][2];
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			//X초 후 C방향으로 회전 (L:왼쪽 / D:오른쪽으로 90도 회전)
			int X = Integer.parseInt(st.nextToken());
			dir[i][0] = X;
			
			char C = st.nextToken().charAt(0);
			//L : -1 , D : 1
			dir[i][1] = (C=='L') ? -1 : 1;	
		}
		
		
		//{1,1}부터 시작
		snake = new LinkedList<>();
        snake.add(new int[]{1, 1});
        int cr=1; int cc=1;
        
		int time = 0;
		int turn = 0;
		
		//뱀이 벽 또는 자기자신의 몸과 부딪히면 게임 종료
		while(true) {
			time++;
			
			int nr = cr + dr[index];
			int nc = cc + dc[index];
			
			//종료 되는지
			if(check(nr,nc)) break;
			
			//사과 있는지
			if(board[nr][nc] == 2) {
				//사과를 먹으면 꼬리는 그대로 머리는 늘려
				board[nr][nc] = 0;
				snake.add(new int[] {nr,nc});
			} else { //사과가 없다면 머리 늘리고 꼬리는 줄여
				snake.add(new int[] {nr,nc});
				snake.remove(0);
			}
			
			//종료 되는지 사과 있는지 다 봤으면 뱀싀 이동
			cr = nr;
			cc = nc;
			
			//방향 바꿔야 되는지
			if(turn<L) {
				if(time == dir[turn][0]) {
					if(dir[turn][1] == -1) { //왼쪽
						index--;
						if(index == -1) index=3;
					}
					if(dir[turn][1] == 1) { //오른쪽
						index++;
						if(index == 4) index=0;
					}
					
					//방향 바뀌면 횟수++
					turn++;
				}
			}
		}
		
		System.out.println(time);
	}


	private static boolean check(int nr, int nc) {
		//벽
		if(nr<1 || nc<1 || nr>=N+1 || nc>=N+1)
			return true;
		
		//자기자신
		for(int i=0; i<snake.size(); i++) {
			if(nr == snake.get(i)[0] && nc == snake.get(i)[1])
				return true;
		}
		
		return false;
	}

}
