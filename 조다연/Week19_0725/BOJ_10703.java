package week19_0725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10703 {
	static int R,S;
	static char[][] map, answer;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		map = new char[R][S];
		answer = new char[R][S];
		
		for(int r=0; r<R; r++) {
			String s = br.readLine();
			for(int c=0; c<S; c++) {
				map[r][c] = s.charAt(c);
				answer[r][c] = map[r][c];
			}
		}
		
		//수직으로 내려갈 수 있는 차이 구하기
		int gap = cal();

		// X : 유성
		// # : 땅
		// . : 공기
		//유성이 원형을 유지한 채 땅 가까이 수직 낙하
		//땅도 변할 수 없음
		fall(gap);
		
		StringBuilder sb = new StringBuilder();
		for(int r=0; r<R; r++) {
			for(int c=0; c<S; c++) {
				sb.append(answer[r][c]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int cal() { 
		int gap = R;
		
		for(int c=0; c<S; c++) {
			int ground = R;
			int meteor = 0;
			for(int r=0; r<R; r++) {
				if(map[r][c]=='X') {
					//유성의 젤 마지막 좌표의 세로 값 갱신
					meteor = Math.max(meteor, r);
				} else if(map[r][c]=='#') {
					//땅의 젤 위 좌표 세로 갱신
					ground = Math.min(ground, r);
				}
			}
			
			//땅의 세로에서 유성 세로를 뺀 만큼 수직 낙하
			//유성이 없을 때 고려해...
			if(meteor!=0) gap = Math.min(gap, ground-meteor-1);
		}
		
		return gap;
	}

	private static void fall(int gap) {
		for(int r=R-1; r>=0; r--) {
			for(int c=0; c<S; c++) {
					if(map[r][c]=='X') {
						//유성이면 차이만큼 아래로 옮겨주기
						answer[r+gap][c] = 'X';
						answer[r][c] = '.';
					}
			}
		}
	}
}
