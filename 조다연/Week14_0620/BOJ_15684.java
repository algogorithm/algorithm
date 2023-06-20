package week14_0620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15684 {
	//��ٸ� ����
	static int N, M, H, ans;
	static int[][] map;
	static boolean isFinish;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //����
		M = Integer.parseInt(st.nextToken()); //����
		H = Integer.parseInt(st.nextToken()); //���μ� ���� ���� �� �ִ� ���μ� ����
		
		
		map= new int[H+1][N+1];
		
		//���� ����
		if(M!=0) {
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				//b�� ���μ��� b+1 ���μ��� a�� ���� ��ġ���� ����
				//0:�������, 1:���� ���μ��� ����ȴ�, 2: ���� ���μ��� ����ȴ�. 
				map[a][b] = 1;
				map[a][b+1] = 2;
			}
			
			//������ 3���� ū ���̸� -1�̶� �� ���Ĵ� ���ǹ�
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
			//i������ ����� i���� �����ϴ��� Ȯ��
			if(check()) isFinish = true;
			return;
		}
		
		for(int i=r; i<= H; i++) {
			for(int j=c; j<N; j++) {
				//���μ� �� ���� �������� ������ �� ����
				//���μ� �߰� �� ����� ���μ��� �ִ��� Ȯ�� �ʿ�
				if(map[i][j] == 0 && map[i][j+1] == 0) {
					//���μ� �߰�
					map[i][j] = 1;
					map[i][j+1] = 2;
					
					dfs(1, 1, n+1);
					
					//�߰��ߴ� ���μ� ���� (��Ʈ��ŷ)
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
				if(map[nr][nc] == 1) nc++; //���� �̵�
				else if(map[nr][nc] == 2) nc--; //���� �̵�
				nr++; //�Ʒ��� �� ĭ �̵�
			}
			//i������ ����ؼ� i������ �������� �ʴ� �� �ϳ��� �ִٸ�
			if(nc != i) return false; 
		}

		return true;
	}
}

