package BAEKJOON;

import java.io.*;
import java.util.*;

public class silver1_3187_양치기꿍 {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int R, C, result_sheep = 0, result_wolf = 0;
	static char[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];

		for (int r = 0; r < map.length; r++) {
			String str = br.readLine();
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = str.charAt(c);
			}
		}


		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(!visit[r][c]) {
					if(map[r][c] == 'v' || map[r][c] == 'k') {
						bfs(new Node(r, c));
					}
				}
			}
		}

		System.out.println(result_sheep + " " + result_wolf);
	}

	private static void bfs(Node node) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(node);
		visit[node.r][node.c] = true;

		int sheep = 0, wolf = 0;

		while(!queue.isEmpty()) {
			Node cur = queue.poll();

			if(map[cur.r][cur.c] == 'v') {
				wolf++;
			} else if(map[cur.r][cur.c] == 'k') {
				sheep++;
			}

			for(int d = 0; d < dr.length; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if(nr>=0 && nr<R && nc>=0 && nc<C && !visit[nr][nc] && map[nr][nc] != '#') {
					visit[nr][nc] = true;
					queue.offer(new Node(nr, nc));
				}
			}
		}

		if(sheep > wolf) {
			result_sheep += sheep;
		} else {
			result_wolf += wolf;
		}
	}

	static class Node{
		int r, c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
