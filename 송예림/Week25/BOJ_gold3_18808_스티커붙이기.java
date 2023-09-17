package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold3_18808_스티커붙이기 {
	static int N, M, K, result = 0;
	static boolean[][] map;
	static ArrayList<int[]> list; // 각 스티커 위치 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			
			// queue에 스티커 위치 저장
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					if(Integer.parseInt(st.nextToken()) == 1) {
						list.add(new int[] {r, c});
					}
				}
			}
			
			// 각 칸 돌면서 스티커 놓을 수 있는지 탐색
			// 스티커 회전...
			/*
			 * 00 01 02 03 04 13 -> c, r*-1
			 * 00 10 20 30 40 3-1 -> c, r*-1
			 * 00 0-1 0-2 0-3 0-4 -1-3 -> c, r*-1
			 * 00 -10 -20 -30 -40 -31
			 * 
			 * 90도 : (r, c) -> (c, r*-1)
			 */
			int cnt = 0;
			while(cnt < 4) {
				if(searchSticker()) {
					break;
				} else {
					rotation();
				}
				cnt++;
			}
			printMap();
		}
		
		countCell();
		System.out.println(result);
	}

	private static void printMap() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] ? 1 + " " : 0 + " ");
			}
			System.out.println();
		}
		System.out.println("====================");
	}

	private static void countCell() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c])
					result++;
			}
		}
	}

	private static void rotation() {
		for (int q = 0; q < list.size(); q++) {
			int[] cur = list.remove(0);
			list.add(new int[] {cur[1], cur[0]*-1});
		}
	}

	private static boolean searchSticker() {
		// 검색하는 동시에 회전해서 넣기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				boolean check = false;
				for (int q = 0; q < list.size(); q++) {
					int[] cur = list.get(q);
					int nr = cur[0] + r;
					int nc = cur[1] + c;
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc]) {
						check = true;
						break;
					}
				}
				if(!check) {
					saveMap(r, c);
					return true;
				}
			}
		}
		
		return false;
	}

	private static void saveMap(int r, int c) {
		for (int q = 0; q < list.size(); q++) {
			int[] cur = list.get(q);
			map[cur[0] + r][cur[1] + c] = true;
		}
	}
}