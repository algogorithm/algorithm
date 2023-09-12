package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class gold2_19238_스타트택시 {
	static int N, M, fuel;
	static int[][] map;
	static Node taxi;
	static ArrayList<Node> start_customer = new ArrayList<>(), end_customer = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxi = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			start_customer.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			end_customer.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		while(true) {
			// 최단 거리 손님 파악
			selectCustomer();
			// 택시 손님한테 이동 -> 이동거리 연료 측정
			
			// 이동가능하면 목적지 이동 -> 이동거리 연료 측정
			
			// 이동가능하면 연료 추가해주기 -> 택시 -> 손님 -> 목적지 이동거리 항상 저장
			
			// 반복 ...
		}
	}

	private static void selectCustomer() {
		
	}

	static class Node {
		int r, c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
