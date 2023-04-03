package Week1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1707_이분_그래프 {
	static boolean isBipartite;
	static int[] vColor;
	static ArrayList<ArrayList<Integer>> graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			vColor = new int[V+1];
			
			for(int i=0; i<V+1; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph.get(from).add(to);
				graph.get(to).add(from);
			}
			
			isBipartite = true;
			
			for(int i=1; i<V+1; i++) {
				if(!isBipartite) {
					break;
				}
				if(vColor[i] == 0) {
					dfs(i, 1);
				}
			}
			
//			System.out.print("vColor : ");
//			for(int i=0; i<vColor.length; i++) {
//				System.out.print(vColor[i]+" ");
//			}System.out.println();
			
			System.out.println(isBipartite ? "YES" : "NO");
		}
	}
	
	private static void dfs(int startV, int color) {
		vColor[startV] = color;
		
		for(int adjV : graph.get(startV)) {
//			System.out.println("정점 : "+startV+" / adjV : "+adjV+" / color : "+color+" / vColor["+adjV+"] : "+vColor[adjV]);
			if(vColor[adjV] == color) {
				isBipartite = false;
				return;
			}
			if(vColor[adjV] == 0) {
				dfs(adjV, -color);
			}
		}
		return;
	}
}
