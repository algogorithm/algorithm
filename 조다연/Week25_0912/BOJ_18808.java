package week25_0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18808 {
	//스티커 붙이기
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
		answer = N*M; //다 붙일 수 있다 했을 때 가능한 수
		
		K = Integer.parseInt(st.nextToken()); //스티커 개수
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
			for(int angle=0; angle<4; angle++) { //0:0도, 1:90도, 2:180도, 3:270도
				if(angle != 0) rotation(sticker);
				
				int sr = sticker.length;
				int sc = sticker[0].length;
				
				for(int r=0; r<N; r++) {
					for(int c=0; c<M; c++) {
						//범위 (스티커 크기만큼)
						if(r+sr>N || c+sc>M) break;
						
						//map의 시작좌표(r,c)~스티커크기만큼(r+sr, c+sc)까지 붙이기 가능한지 check 
						if(check(r, c, sr, sc)) {
							//가능하면 붙여주기
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
		
		//전체 수에서 0인 개수 빼서 구하기
		int zero = 0;
		for(int[] arr : map) {
			for(int n : arr) {
				if(n==0) zero++;
			}
		}

		System.out.println(answer-zero);
	}
	
	//스티커 들어갈 수 있는지 확인
	private static boolean check(int r, int c, int sr, int sc) {
		for(int i=0; i<sr; i++) {
			for(int j=0; j<sc; j++) {
				//스티커를 붙여야 되는데 이미 붙어 있다면 못 붙임
				if(sticker[i][j]==1 && map[i+r][j+c]==1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	//노트북에 해당 스티커 넣어주기
	private static void attach(int r, int c) {
		for(int i=r,x=0,y=0; i<r+sticker.length; i++) {
			for(int j=c; j<c+sticker[0].length; j++) {
				//범위
				if(i<0 || i>=map.length || j<0 || j>=map[0].length) continue;
					
				if(map[i][j]==0) {
					map[i][j] = sticker[x][y];
				}
				y++;
			}
			x++; y=0;
		}
	}

	//90도씩 회전
	private static void rotation(int[][] arr) {
		//복사해둘 배열
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
			
		//회전한 내용으로 -
		sticker = new int[arr[0].length][arr.length];
		copy(tmp);
	}

	//깊은 복사
	private static void copy(int[][] tmp) {
		for(int r=0; r<tmp.length; r++) {
			for(int c=0; c<tmp[0].length; c++) {
				sticker[r][c] = tmp[r][c];
			}
		}
	}
}
