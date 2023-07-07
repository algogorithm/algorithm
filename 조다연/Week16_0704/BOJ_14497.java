package week16_0704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_14497 {
	//�ֳ��� ��
	static int N, M;
	static int x1, y1, x2, y2;
	static char[][] map;
	//					�� �� �� ��
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1}; 

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		//�ֳ�
		x1 = Integer.parseInt(st.nextToken())-1;
		y1 = Integer.parseInt(st.nextToken())-1;
		//����
		x2 = Integer.parseInt(st.nextToken())-1;
		y2 = Integer.parseInt(st.nextToken())-1;
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		System.out.println(jump());
	}

	// 0-1 BFS : ����ġ�� 0�� 1�θ� �־��� �׷������� �ִ� ��θ� ã���� �� ��
	private static int jump() {
		Deque<int[]> dq = new LinkedList<int[]>();
		boolean[][] v = new boolean[N][M];
		v[x1][y1] = true;
		dq.add(new int[] {x1, y1, 0});
		
		while(!dq.isEmpty()) {
			int[] a = dq.poll();
			
			//���ڹ��� ��ġ�� ����ġ return
			if(a[0] == x2 && a[1] == y2) return a[2];
			
			//�����¿� Ž��
			for(int d=0; d<4; d++) {
				int nr = a[0]+dr[d];
				int nc = a[1]+dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || v[nr][nc]) continue;
				
				v[nr][nc] = true;
				
				// ���� ������ �Һ�� ��� + �� ��带 ���ϴ� ����ġ < �� ������ ���µ� �Һ�� ��� �̸� �Һ�� ����� ����
				//��带 ���ϴ� ����ġ�� 0�̸� ���� �� �տ�, ����ġ�� 1�̸� ���� �� �ڿ� �߰�
				
				//���� ������ 0�̶��, �� ���� ������ ���� �����ϹǷ� ���� ���������� ���� Ƚ���� ����
				if(map[nr][nc] == '0') dq.addFirst(new int[] {nr, nc, a[2]});
				//���� ������ 1�̶��, �ĵ��� ���߹Ƿ� (���� ���������� ���� Ƚ��+1)�� ����
				else dq.addLast(new int[] {nr, nc, a[2]+1});
			}
		}
		
		return 0;
	}

}
