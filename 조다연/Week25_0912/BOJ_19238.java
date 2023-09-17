package week25_0912;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19238 {
	//��ŸƮ �ý�
	static int N, M, fuel;
	static int taxiR, taxiC;
	static int[][] map;
	static boolean[][] v;
	static int[][] person;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		//�ý��� ù ��ġ
		taxiR = Integer.parseInt(st.nextToken());
		taxiC = Integer.parseInt(st.nextToken());
		
		person = new int[M+1][4]; //��� ��,�� / ���� ��,��
		for(int r=1; r<=M; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<4; c++) {
				person[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		//1. ���� �ý� ��ġ���� ���� ����� �մ� ��ġ �ľ�
		//2. �°� ��ġ���� �� �°��� ���� ��ġ������ �ִܰŸ� ���ϰ� ���ֱ�
		//����>=0�̸� �ִܰŸ�x2��ŭ ���ῡ �����ֱ� 
		//123 �ݺ��ϴٰ� �մ� �� �̵� �������� ���� ���� ���
		//����<0�̸� ���� -1 ���
		
		//�ýÿ��� ù��° �մ� �¿��
		//�ִܰŸ��� ���� ª -> �� -> ��
		//person[M][0]person[M][1]
		int p_cnt = M; //�¿� ��� �� 0 �Ǹ� ����
		boolean[] a = new boolean[M+1];
		boolean flag = false;
		
		while(p_cnt>0) {
			//1. ���� �ý� ��ġ���� ���� ����� �մ� ��ġ �ľ�
			int d = Integer.MAX_VALUE; int f_r = 0, f_c=0, idx=0;
			for(int i=1; i<=M; i++) {
				
				if(a[i]) continue;
				
				int dt = find(person[i][0], person[i][1]);
				
				//�ִܰŸ��� �ȳ����� �� �� ���ٴ� ��
				if(dt==-1) {
					flag = true;
					break;
				}
				
				//�Ÿ��� ������ �ٰ��� �°��� ��ġ�� �Ÿ��� ���س�
				if(d>dt) {
					d = Math.min(d, dt);
					f_r = person[i][0];
					f_c = person[i][1];
					idx = i;
				} else if(d==dt) {
					//�ִܰŸ��� ������ ������ �� �� ��ȣ��
					if(f_r>person[i][0]) {
						f_r = person[i][0];
						f_c = person[i][1];
						idx = i;
					} else if(f_r==person[i][0]) {
						if(f_c>person[i][1]) {
							f_r = person[i][0];
							f_c = person[i][1];
							idx = i;
						}
					}
				}
			}
			
			if(flag) break;
			
			//2. �¿� �°� ��ġ���� �� �°��� ���� ��ġ������ �ִܰŸ� ���ϰ� ���� ���ֱ�
			fuel -= d;
			taxiR = f_r; taxiC =f_c;
			int dt = find(person[idx][2], person[idx][3]);
			fuel -= dt;
			
			if(fuel>=0) {
				//����>=0�̸� �ִܰŸ�x2��ŭ ���ῡ �����ֱ� 
				fuel += dt*2;
				p_cnt--; //�մ� �� �¿� ������ 123 �ݺ�
				taxiR = person[idx][2]; taxiC =person[idx][3];
				a[idx] = true;
			} else {
				//����<0�̸� ���� -1 ���
				fuel = -1;
				break;
			}

		}
		
		//�� ���̶� �� ���ٸ� -1
		if(flag) fuel = -1;
		System.out.println(fuel);
	}

	private static int find(int r, int c) {
		v = new boolean[N+1][N+1];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c, 0));
		v[r][c] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int cr = p.r; int cc = p.c;
			
			if(cr==taxiR && cc==taxiC) {
				return p.dt;
			}
			
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				//���� �湮 ��
				if(nr<=0 || nr>N || nc<=0 || nc>N) continue;
				if(v[nr][nc]) continue;
				if(map[nr][nc]==1) continue;
				
				q.add(new Point(nr, nc, p.dt+1));
				v[nr][nc] = true;
			}
		}
		
		return -1;
	}

	static class Point {
		int r;
		int c;
		int dt;
		
		public Point(int r, int c, int dt) {
			super();
			this.r = r;
			this.c = c;
			this.dt  = dt;
		}
	}
}
