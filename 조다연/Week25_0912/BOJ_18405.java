package week25_0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_18405 {
	//������ ����
	static int N, K;
	static int[][] map;
	static boolean[][] v;    
	static boolean[][] a;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static ArrayList<ArrayList<Point>> virus;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		//�ʱ�ȭ - �� ��ȣ ���̷��� ��ǥ ����
		virus = new ArrayList<>();
		for(int i=0; i<=K; i++) {
			virus.add(new ArrayList<Point>());
		}
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c]!=0) {
					virus.get(map[r][c]).add(new Point(r,c));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); 
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());		

		//��ü������ �� ���� ������ ��ģ ���̷��� �ڸ� ǥ��
		a = new boolean[N][N];
		while(S-- > 0) {
			v = new boolean[N][N]; //�ش� �ʿ� �湮 ǥ��
			
			for(int idx=1; idx<=K; idx++) {
				for(int i=0; i<virus.get(idx).size(); i++) {
					//���̷��� ���� ���۵Ǵ� ��ǥ
					int r = virus.get(idx).get(i).r;
					int c = virus.get(idx).get(i).c;
					
					if(v[r][c]) continue;
					if(a[r][c]) continue;
					
					//�����ض�
					increase(r, c, idx);
					
					//�ϴٰ� ���̷����� �̹� ���� �Ǿ��ٸ� ���� �� �� �ʿ� ����
					if(map[X-1][Y-1]!=0) S=0;
				}
			}
		}
		
		
		//S�� �� (X,Y)�� ��ġ�� ���̷��� ���
		System.out.println(map[X-1][Y-1]);
	}

	private static void increase(int r, int c, int idx) {
		v[r][c] = true;
		a[r][c] = true;
		
		//�����¿�� ����
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			//���� �湮
			if(nr<0 || nr>=N || nc<0 || nc>=N || v[nr][nc]) continue;
			
			//�� ���̸� ���̷��� ����
			//���� ���� ���̷����� �ƹ��͵� �ϣp�� �� ������ �� ���� ������
			//�ٸ� ���̷��� ������ ���� ������
			if(map[nr][nc]==0) {
				map[nr][nc] = idx;
				v[nr][nc] = true;
				//virus�� �� �־��ּ�
				virus.get(idx).add(new Point(nr,nc));
			}
		}
	}
	
	static class Point {
		int r;
		int c;
		
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}
