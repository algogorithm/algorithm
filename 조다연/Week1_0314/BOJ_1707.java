package day1_0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1707 {
	static ArrayList<ArrayList<Integer>> graph;
	static int color[];//0:방문 x, 1, -1
	static int RED=1;
	static String ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		
		for(int t=0; t<K; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			//정점 개수
			int V = Integer.parseInt(st.nextToken());
			//간선 개수
			int E = Integer.parseInt(st.nextToken());
			
			//초기화
			graph = new ArrayList<>();
			color = new int[V+1];
			for(int i=0; i<=V; i++) {
				graph.add(new ArrayList<>());
			}
			
			//간선에 대한 정보
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				//양방향 연결해주기
				graph.get(from).add(to);
				graph.get(to).add(from);
			}
			
			ans = "YES";
			//색칠 안된 모든 정점에 대해서 bfs 탐색
			for(int i=1; i<=V; i++) {
				if(color[i]==0) {
					if(!bfs(i)) break;
				}
			}
			
			System.out.println(ans);
		}

	}

	private static boolean bfs(int i) {
		Queue<Integer> q = new LinkedList<>();
		q.add(i);
		color[i] = RED;
		
		while(!q.isEmpty()) {
			int node = q.poll();
			
			for(int next : graph.get(node)) {
				//인접 정점의 색이 같다면 이분 그래프 x
				if(color[node] == color[next]) {
					ans = "NO";
					return false;
				}
				
				//색칠 되어 있지 않다면 자신의 색깔과 반대로
				if(color[next]==0) {
					color[next] = color[node]*-1;
					q.add(next);
				}
			}
		}
		
		return true;
	}

}
