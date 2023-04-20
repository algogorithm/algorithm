package Week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14218_그래프_탐색_2 {
	static ArrayList<Integer>[] GRAPH;
	static int[][] result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		GRAPH = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			GRAPH[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
		
			GRAPH[to].add(from);
			GRAPH[from].add(to);
		}

		int makeNewRoads = Integer.parseInt(br.readLine());
		result = new int[makeNewRoads][n+1];
		for(int i=0; i<result.length; i++) {
			Arrays.fill(result[i], 999999);
		}
		
		for(int i=0; i<makeNewRoads; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());

			GRAPH[to].add(from);
			GRAPH[from].add(to);
			
			bfs(i, 1, new boolean[GRAPH.length]);
		}
		
		for(int i=0; i<result.length; i++) {
			for(int j=1; j<result[0].length; j++) {
				sb.append(result[i][j]+" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	
	private static void bfs(int testOrder, int startV, boolean[] v) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {startV, 0});
		v[startV] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			if(result[testOrder][now[0]] == 999999) {
				result[testOrder][now[0]] = now[1];
			}
			
			for(int i=0; i<GRAPH[now[0]].size(); i++) {
				int element = GRAPH[now[0]].get(i);
				if(!v[element]) {
					v[element] = true;
					q.add(new int[] {element, now[1]+1});
				}
			}
		}
		
		for(int i=1; i<v.length; i++) {
			if(result[testOrder][i] == 999999) {
				result[testOrder][i] = -1;
			}
		}
		
	}
}
