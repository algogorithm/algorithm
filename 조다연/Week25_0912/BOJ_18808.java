package week25_0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18808 {
	//��ƼĿ ���̱�
	static int N, M, K;
	static int[][] map;
	static int[][] sticker;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		answer = N*M; //�� ���� �� �ִ� ���� �� ������ ��
		
		K = Integer.parseInt(st.nextToken()); //��ƼĿ ����
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			sticker = new int[R][C];
			
			for(int r=0; r<R; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<C; c++) {
					sticker[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean flag = false;
			for(int angle=0; angle<4; angle++) { //0:0��, 1:90��, 2:180��, 3:270��
				if(angle != 0) rotation(sticker);
				
				int sr = sticker.length;
				int sc = sticker[0].length;
				
				for(int r=0; r<N; r++) {
					for(int c=0; c<M; c++) {
						//���� (��ƼĿ ũ�⸸ŭ)
						if(r+sr>N || c+sc>M) break;
						
						//map�� ������ǥ(r,c)~��ƼĿũ�⸸ŭ(r+sr, c+sc)���� ���̱� �������� check 
						if(check(r, c, sr, sc)) {
							//�����ϸ� �ٿ��ֱ�
							attach(r, c);
							flag = true;
							break;
						}
					}
					if(flag) break;
				}
				if(flag) break;
			}
		}
		
		//��ü ������ 0�� ���� ���� ���ϱ�
		int zero = 0;
		for(int[] arr : map) {
			for(int n : arr) {
				if(n==0) zero++;
			}
		}

		System.out.println(answer-zero);
	}
	
	//��ƼĿ �� �� �ִ��� Ȯ��
	private static boolean check(int r, int c, int sr, int sc) {
		for(int i=0; i<sr; i++) {
			for(int j=0; j<sc; j++) {
				//��ƼĿ�� �ٿ��� �Ǵµ� �̹� �پ� �ִٸ� �� ����
				if(sticker[i][j]==1 && map[i+r][j+c]==1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	//��Ʈ�Ͽ� �ش� ��ƼĿ �־��ֱ�
	private static void attach(int r, int c) {
		for(int i=r,x=0,y=0; i<r+sticker.length; i++) {
			for(int j=c; j<c+sticker[0].length; j++) {
				//����
				if(i<0 || i>=map.length || j<0 || j>=map[0].length) continue;
					
				if(map[i][j]==0) {
					map[i][j] = sticker[x][y];
				}
				y++;
			}
			x++; y=0;
		}
	}

	//90���� ȸ��
	private static void rotation(int[][] arr) {
		//�����ص� �迭
		int[][] tmp;
		int tr; int tc;

		///r <-> c
		tmp = new int[arr[0].length][arr.length];
		tr = 0; tc=tmp[0].length-1;
			
		for(int r=0; r<arr.length; r++) {
			for(int c=0; c<arr[0].length; c++) {
				tmp[tr++][tc] = arr[r][c];
			}
			tr = 0; tc--;
		}
			
		//ȸ���� �������� -
		sticker = new int[arr[0].length][arr.length];
		copy(tmp);
	}

	//���� ����
	private static void copy(int[][] tmp) {
		for(int r=0; r<tmp.length; r++) {
			for(int c=0; c<tmp[0].length; c++) {
				sticker[r][c] = tmp[r][c];
			}
		}
	}
}
