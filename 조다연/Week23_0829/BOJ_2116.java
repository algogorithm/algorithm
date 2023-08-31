package week23_0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2116 {
	//�ֻ��� �ױ�
	static int N;
	static int answer;
	static int[][] dice;
	//a(0)-f(5) / b(1)-d(3) / c(2)-e(4)
	static int[] set = {5,3,4,1,2,0};

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//�ֻ��� ����
		N = Integer.parseInt(br.readLine());
		dice = new int[N][6];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<6; j++) {
				//0:a, 1:b, 2:c, 3:d, 4:e, 5:f
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		//�������� �簢���� ���� ū ���� �Ƿ���
		//�� ó���� �״� �ֻ����� 1�� �� ���� �ξ�� ��
		for(int i=1; i<=6; i++) {
			find(0, i, 0);
		}
		
		
		System.out.println(answer);
	}

	private static void find(int n, int down, int sum) {
		//�ֻ����� ��� �� ������ �׸�
		if(n==N) {
			answer = Math.max(answer, sum);
			return;
		}

		int up = findUp(n, down);
		
		//���Ʒ��� 5,6/6,5�� �� ū �� 4
		//������ 6�̸� �� ū�� 5
		//�� �� 6
		int max = 0; 
		if(up+down==11) {
			max = 4;
		}else if(up==6||down==6) {
			max = 5;
		} else max = 6;
		
		//���� �ֻ����� ���� ���� �ֻ����� �Ʒ� ���ڰ� ��
		find(n+1, up, sum+max);
	}

	private static int findUp(int n, int down) {
		//���� �ֻ����� ���� ���� �ֻ����� �Ʒ��� ���� ã����� ��  (¦�� ����)
		//���� �ֻ����� �Ʒ��� 1�̾�.. 
		//dice[1][1] = 1����?
		//1:3�̴ϱ� dice[1][3]=4��
		//4�� �Ѱ���� ��
		int idx = 0;
		for(int i=0; i<dice[n].length; i++) {
			if(dice[n][i]==down) {
				idx = i;
				break;
			}
		}
		return dice[n][set[idx]];
	}

}
