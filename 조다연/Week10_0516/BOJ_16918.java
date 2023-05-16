package week10_0516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16918 {
	//봄버맨
	static int R, C, N;
	static char[][] map;
	static char[][] tmp, arr;
	static boolean[][] v;
	//					상 하 좌 우
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken()); //초
		
		map = new char[R][C];
		tmp = new char[R][C];
		arr = new char[R][C];
		
		for(int r=0; r<R; r++) {
			String s = br.readLine();
			for(int c=0; c<C; c++) {
				map[r][c] = s.charAt(c);
				tmp[r][c] = 'O';
			}
		}
		copy(arr, tmp);

		if(N==1) {
			print(map);
		} else if(N==2) {
			print(arr);
		} else {
			int time = 1; //초기상태 1초 후
			
			while(time<N) {
				v = new boolean[R][C];
				//이거슨 2초 후 랄가--
				//상하좌우 O로 채워주기
				for(int r=0; r<R; r++) {
					for(int c=0; c<C; c++) {
						if(map[r][c] == 'O' && !v[r][c]) {
							v[r][c] = true;
							for(int d=0; d<4; d++) {
								int nr = r+dr[d];
								int nc = c+dc[d];
								
								if(nr<R && nr>=0 && nc<C && nc>=0 && !v[nr][nc] && map[nr][nc]=='.') {
									map[nr][nc] = 'O';
									v[nr][nc] = true;
								}
							}
						}
					}
				}
				time++;
				//--
				
				if(time==N) break;
				
				//이거슨 3초--
				//꽉 채워진 맵에서 폭탄 설치된 맵의 O좌표를 활용해 .로 만들어주기
				for(int r=0; r<R; r++) {
					for(int c=0; c<C; c++) {
						if(map[r][c] == 'O') {
							tmp[r][c] = '.';
						}
					}
				}

				//그것을 원본으로 만들어주기
				copy(map, tmp);
				copy(tmp, arr);		
				time++;
				//--
			}

			//변화된 맵 출력하기
			if(time%2==0) {
				//짝수 초마다 폭탄 와다다
				print(arr);
			} else {
				print(map);
			}
		}
	}

	private static void copy(char[][] change, char[][] origin) {
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				change[r][c] = origin[r][c];
			}
		}
	}

	private static void print(char[][] map) {
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
	}

}
