package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold3_16235_나무재테크 {
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int N, M, K;
	static int[][] map;
	static int[][] A;
	// ArrayList 경우 remove() 할 때, 내부의 배열이 갱신될 수 있으므로 주의할 것
	// LinkedList 경우 특정 인덱스에 접근할 때, 순차적으로 탐색해서 접근하기 때문에 중간 인덱스 값을 탐색한다면 사용X
	static PriorityQueue<Tree> first = new PriorityQueue<>();
	static Deque<Tree> list = new LinkedList<>();
	static Queue<Tree> dieTree = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		A = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			first.add(new Tree(r, c, age));
		}
		
		while(!first.isEmpty()) {
			list.add(first.poll());
		}
		
		for (int i = 0; i < K; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		
		System.out.println(list.size());
	}

	private static void winter() {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] += A[r][c];
			}
		}
	}

	private static void fall() {
		Queue<Tree> tmp = new LinkedList<>();
		for (Tree cur : list) {
			if(cur.age % 5 == 0) {
				tmp.offer(cur);
			}
		}
		
		while(!tmp.isEmpty()) {
			Tree cur = tmp.poll();
			for (int d = 0; d < dr.length; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if(nr>0 && nr<=N && nc>0 && nc<=N) {
					list.offerFirst(new Tree(nr, nc, 1)); // offer() 은 마지막에 원소 넣음 주의..!!!!!!!
				}
			}
		}
		
	}

	private static void summer() {
		while(!dieTree.isEmpty()) {
			Tree cur = dieTree.poll();
			map[cur.r][cur.c] += cur.age/2; 
		}
	}

	private static void spring() {
		int s = list.size();
		for (int i = 0; i < s; i++) {
			Tree cur = list.poll();
			if(map[cur.r][cur.c] >= cur.age) {
				map[cur.r][cur.c] -= cur.age;
				cur.age++;
				list.add(cur);
			} else {
				dieTree.offer(cur);
			}
		}
	}

	static class Tree implements Comparable<Tree>{
		int r, c, age;
		
		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age, o.age);
		}
	}
}