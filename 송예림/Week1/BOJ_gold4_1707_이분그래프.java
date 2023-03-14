package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold4_1707_이분그래프 {
	static int V, E;
	static ArrayList<Integer>[] node;
	static int[] visit;
	static boolean result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int K = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < K; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			node = new ArrayList[V+1];
			visit = new int[V+1]; // 1, 2로 구분하기
			result = true;
			for (int i = 1; i <= V; i++) {
				node[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				node[a].add(b);
				node[b].add(a);
			}
			
			for (int i = 1; i < V+1; i++) {
				if(!result) {
					break;
				}
				// 방문 안했으면 탐색
				if(visit[i] == 0) {
					bfs(i);
				}
			}
			if(result) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
			
//			sb.append(result ? "YES" : "NO").append("\n");
		}
		
		System.out.print(sb);
	}

	private static void bfs(int i) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(i);
		visit[i] = 1;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
//			System.out.println(cur);
			
			for (int j = 0; j < node[cur].size(); j++) {
				if(visit[node[cur].get(j)] == 0) {
					visit[node[cur].get(j)] = visit[cur] == 1 ? 2 : 1;
					queue.offer(node[cur].get(j));
				}
				else if(visit[node[cur].get(j)] == visit[cur]) {
					result = false;
					return;
				}
			}
		}
	}

}