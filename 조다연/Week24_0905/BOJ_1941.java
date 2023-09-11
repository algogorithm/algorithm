package week24_0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1941 {
	//�ҹ��� ĥ����
	static char[][] student = new char[5][5];
	static Point[] point = new Point[25];
	static boolean[] v = new boolean[25];
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int idx=0;
		for(int r=0; r<5; r++) {
			student[r] = br.readLine().toCharArray();
			for(int c=0; c<5; c++) {
				point[idx++] = new Point(r, c);
			}
		}
		
//		print(student);
		
		//25������ 7���� �̾�
		//���� ���տ���
		//s�� 4�̻�, ���� ���ΰ� �������� ���� �Ǵ�
		answer = 0;
		combination(0, 0, null, 0);
		
		System.out.println(answer);
	}

	private static void combination(int idx, int cnt, Point p, int sCnt) {
		//���� 7����
		if(cnt==7) {
			//s>=4�̸� bfs�� ����Ǿ� �ִ��� Ž��
			if(sCnt>=4 && check(p)) {
				answer++;
			}
			return;
		}
		
		for(int i=idx; i<point.length; i++) {
			if(!v[i]) {
				v[i] = true;
				p = point[i];
				
				if(student[p.r][p.c] == 'S') {
					student[p.r][p.c] = 'A';
					combination(i, cnt+1, p, sCnt+1);
					student[p.r][p.c] = 'S'; //�ǵ���
				} else {
					student[p.r][p.c] = 'A';
					combination(i, cnt+1, p, sCnt);
					student[p.r][p.c] = 'Y'; //�ǵ���
				}
				
				v[i] = false; //�ǵ���
			}
		}
		
	}

	//���μ��� ����
	private static boolean check(Point p) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(p);
		boolean[][] v = new boolean[5][5];
		v[p.r][p.c] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			int cr = now.r; int cc = now.c;
			
			if(cnt==7) return true;
			
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				//���� �湮 ������ �л�
				if(nr<0 || nr>=5 || nc<0 || nc>=5) continue;
				if(v[nr][nc]) continue;
				if(student[nr][nc]!='A') continue;
				
				q.add(new Point(nr, nc));
				v[nr][nc] = true;
				cnt++;
			}
		}
		
		return false;
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
