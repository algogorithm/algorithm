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
		
		//�������� ������ �� �ִ� ���� ���ϱ�
		int gap = cal();

		// X : ����
		// # : ��
		// . : ����
		//������ ������ ������ ä �� ������ ���� ����
		//���� ���� �� ����
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
					//������ �� ������ ��ǥ�� ���� �� ����
					meteor = Math.max(meteor, r);
				} else if(map[r][c]=='#') {
					//���� �� �� ��ǥ ���� ����
					ground = Math.min(ground, r);
				}
			}
			
			//���� ���ο��� ���� ���θ� �� ��ŭ ���� ����
			//������ ���� �� �����...
			if(meteor!=0) gap = Math.min(gap, ground-meteor-1);
		}
		
		return gap;
	}

	private static void fall(int gap) {
		for(int r=R-1; r>=0; r--) {
			for(int c=0; c<S; c++) {
					if(map[r][c]=='X') {
						//�����̸� ���̸�ŭ �Ʒ��� �Ű��ֱ�
						answer[r+gap][c] = 'X';
						answer[r][c] = '.';
					}
			}
		}
	}
}
