package Week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14218_그래프_탐색_2 {
	static ArrayList<ArrayList<Integer>> GRAPH;
	static int[][] result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		GRAPH = new ArrayList<>();
		for(int i=0; i<=n; i++) {
			GRAPH.add(new ArrayList<>());
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
		
			GRAPH.get(to).add(from);
			GRAPH.get(from).add(to);
		}

		int makeNewRoads = Integer.parseInt(br.readLine());
		result = new int[makeNewRoads][n+1];
		for(int i=0; i<result.length; i++) {
			Arrays.fill(result[i], -1);
		}
		
		for(int i=0; i<makeNewRoads; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			
			GRAPH.get(to).add(from);
			GRAPH.get(from).add(to);
			
			dfs(makeNewRoads, 1, 0, new boolean[GRAPH.size()]);				
		}
		
		for(int i=0; i<result.length; i++) {
			for(int j=0; j<result[0].length; j++) {
				System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
	}
	private static void dfs(int order, int location, int cnt, boolean[] v) {
		if(order == 1) {
			result[order][location] = cnt;
			return;
		}
		v[order] = true;
		
		for(int i : GRAPH.get(location)) {
			if(!v[i]) {
				dfs(i, location, cnt+1, v);				
			}
		}
		
	}

}
