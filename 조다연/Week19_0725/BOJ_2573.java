package week19_0725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {
	static int N, M;
	static int[][] arr;
	static int[][] sea;
	//					�� �� �� ��
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] v;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		sea = new int[N][M];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		
		while(true) {
			//ó���� �ϴ� �����¿� �����ִ� �ٴ� ������ŭ �쿩�ֱ�
			melt();
			
			//�ð� �帧
			time++;
			
			//�и��Ǵ� ���� ���� ���ϱ�
			int cnt = checkSize();
			
			if(cnt==0) {//������ �� ���� ������ �и����� ������ 0 ���
				System.out.println("0");
				break;
			} else if(cnt>=2) {//�� ��� �̻����� �и��Ǵ� ������ �ð� ���
				System.out.println(time);
				break;
			}
		}
	}

	//���� ���̱�
	private static void melt() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				//�ٴ��� �� ������
				if(arr[r][c]==0) continue;
				
				//0 �ƴ� ������ ��, �����¿� Ž���Ͽ� �ٴ��� ����������ŭ ���ֱ�
				int cnt = 0;
				int cr = r; int cc = c;
				for(int d=0; d<4; d++) {
					int nr = cr+dr[d];
					int nc = cc+dc[d];

					if(check(nr, nc) && arr[nr][nc]==0) cnt++;
				}

				sea[r][c] = arr[r][c] - cnt;
				
				//����� ���̴� 0���� �پ���� ����
				if(sea[r][c]<0) sea[r][c] = 0;
			}
		}
		copy();
	}
	
	//���� ����
	private static void copy() {
		for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                arr[i][j] = sea[i][j];  
            }
        }
	}

	//�и��� ��� ����
	private static int checkSize() {
		int cnt = 0;
		v = new boolean[N][M];
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				//�ٴٰ� �ƴϰ� �湮�� �� ���� ��ǥ���� �ѵ������ Ž��
				if(arr[r][c]>0 && !v[r][c]) {
					cnt++;
					bfs(r,c);
				}
			}
		}
		return cnt;
	}

	//�̾����ִ��� Ž��
	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		
		q.add(new int[] {r,c});
		v[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();

			for(int d=0; d<4; d++) {
				int nr = now[0]+dr[d];
				int nc = now[1]+dc[d];
				
				if(check(nr,nc) && !v[nr][nc] && arr[nr][nc]>0) {
					q.add(new int[] {nr,nc});
					v[nr][nc] = true;
				}
			}	
		}
	}

	//���� üũ
	private static boolean check(int nr, int nc) {
		if(nr>=0 && nr<N && nc>=0 && nc<M) {
			return true;
		}
		return false;
	}

}
