package week14_0620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144 {
	//�̼����� �ȳ�!
	static int R,C,T;
	static int[][] map;
	static int[][] dust;
	//					�� �� �� ��
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		int up_row = 0; int down_row = 0; 
		
		map = new int[R][C];
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] ==  -1) { //����û���� ��ġ
					if(up_row == 0) {
						up_row = r;
					} else {
						down_row = r;
					}
				}
			}
		}
		
		for(int t=0; t<T; t++) {
			dust = new int[R][C];
			//�̼����� ��� ĭ���� Ȯ��
			//���ڰ� �ִ� ĭ(0�� �ƴ� ĭ)�� �׹��� Ž�� �� ������ ���� Ȯ���
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					if(map[r][c] != 0) {
						spread(r,c);
					}
				}
			}
			
			map = new int[R][C];
			//����û���� �ٶ� ��
			//���� �ݽð�������� �� ĭ�� �̵�
			//���� ����û���� �ִ� ��
			for(int c=1; c<C-1; c++) {
				//������ �ִ� ĭ�̸� �� -
				if(dust[up_row][c] != 0) {
					map[up_row][c+1] = dust[up_row][c];
				}
			}
			
			for(int r=up_row; r>0; r--) {
				//������ �ִ� ĭ�̸� �� -
				if(dust[r][C-1] != 0) {
					map[r-1][C-1] = dust[r][C-1];
				}
			}
			
			for(int c=C-1; c>0; c--) {
				//������ �ִ� ĭ�̸� �� -
				if(dust[0][c] != 0) {
					map[0][c-1] = dust[0][c];
				}
			}
			
			for(int r=0; r<up_row-1; r++) {
				//������ �ִ� ĭ�̸� �� -
				if(dust[r][0] != 0) {
					map[r+1][0] = dust[r][0];
				}
			}

			//�Ʒ��� �ð�������� �� ĭ�� �̵�
			for(int c=1; c<C-1; c++) {
				//������ �ִ� ĭ�̸� �� -
				if(dust[down_row][c] != 0) {
					map[down_row][c+1] = dust[down_row][c];
				}
			}
			
			for(int r=down_row; r<R-1; r++) {
				//������ �ִ� ĭ�̸� �� -
				if(dust[r][C-1] != 0) {
					map[r+1][C-1] = dust[r][C-1];
				}
			}
			
			for(int c=C-1; c>0; c--) {
				//������ �ִ� ĭ�̸� �� -
				if(dust[R-1][c] != 0) {
					map[R-1][c-1] = dust[R-1][c];
				}
			}
			
			for(int r=R-1; r>down_row+1; r--) {
				//������ �ִ� ĭ�̸� �� -
				if(dust[r][0] != 0) {
					map[r-1][0] = dust[r][0];
				}
			}
			
			//�̼����� ���� ���� �κ�
			for(int r=1; r<R-1; r++) {
				for(int c=1; c<C-1; c++) {
					if(r==up_row || r==down_row) continue;
					
					map[r][c] = dust[r][c];
				}
			}
		}

		//�̼����� �� ���
		int sum = 0;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				sum+=map[r][c];
			}
		}
		
		System.out.println(sum);
	}

	private static void spread(int r, int c) {
		//Ȯ�� ������ ĭ�� ��
		int cnt = 0; 
		
		//4��Ž��
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			//���� ���� ����û���� ��ġ�� �ƴϸ� �̼����� Ȯ�� ����
			if(check(nr,nc) && map[nr][nc] != -1) {
				cnt++;
				//������ �� + Ȯ��� ��
				dust[nr][nc] += map[r][c]/5;
				
			}
		}
		
		//���� ������ Ȯ��� �縸ŭ ���ֱ�
		dust[r][c] += map[r][c] - ((map[r][c]/5)*cnt);
	}

	private static boolean check(int nr, int nc) {
		if(nr>=0 && nr<R && nc>=0 && nc<C) {
			return true;
		}
		return false;
	}

	private static void print(int[][] map) {
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				System.out.print(map[r][c]+" ");
			}
			System.out.println();
		}
	}

}
