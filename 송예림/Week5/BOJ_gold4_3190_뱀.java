package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold4_3190_뱀 {
	// 왼쪽 -1, 오른쪽 +1
	// 상 우 하 좌
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int N, K, L, dir_snake=1, result = 0, cnt = 0;
//	static Node head = new Node(0, 0);
//	static Node tail = new Node(0, 0);
	static int[][] board;
	static Queue<Dir> queue_dir;
//	static List<Node> apple = new ArrayList<>();
	static List<Node> snake = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		// 사과 위치 -> 1
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
//			apple.add(new Node(r, c));
			board[r][c] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		queue_dir = new LinkedList<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			queue_dir.offer(new Dir(time, dir));
		}
//		Collections.sort(list_dir);
//		System.out.println(list_dir.peek().time);
		
		// 뱀 위치 -> 2
		board[1][1] = 2;
		snake.add(new Node(1,1));

		while(check()) {
			// 벽 또는 자기자신의 몸과 부딪히는지 check
//			check();
			// 머리 옮기기
			go_head();
			
			cnt++;
			// 회전 확인
			if(!queue_dir.isEmpty()) {
				check_dir();
			}
		}
		System.out.println(cnt+1);
	}

	private static void go_head() {
		Node n = snake.get(snake.size()-1);
		int nr = n.r + dr[dir_snake];
		int nc = n.c + dc[dir_snake];

		snake.add(new Node(nr, nc));
//		board[nr][nc] = 2;
		
		// 사과있으면 사과 지우기
		if(board[nr][nc] == 1) {
//			System.out.println(apple.contains(new Node(nr, nc)));
//			apple.remove(new Node(nr, nc));
			board[nr][nc] = 2;
		} else { // 사과 없으면 꼬리 줄이기
			Node rem = snake.remove(0);
			board[rem.r][rem.c] = 0;
			board[nr][nc] = 2;
			// 뱀이 이동한 위치 list로 둬야할듯 -> 꼬리빼야함
//			tail = new Node(tail.r+)
		}
	}

	private static void check_dir() {
		if(cnt == queue_dir.peek().time) {
			String str = queue_dir.poll().dir;
			if(str.equals("L")) {
				dir_snake = (dir_snake+3) % 4;
			} else {
				dir_snake = (dir_snake+1) % 4;
			}
		}
	}

	private static boolean check() {
		Node n = snake.get(snake.size()-1);
		int nr = n.r + dr[dir_snake];
		int nc = n.c + dc[dir_snake];
		if(nr>0 && nr<=N && nc>0 && nc<=N && board[nr][nc] != 2) {
			return true;
		}
		return false;
	}

	public static class Node{
		int r, c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static class Dir{
		int time;
		String dir;
		public Dir(int time, String dir) {
			this.time = time;
			this.dir = dir;
		}
	}
}
