package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_1707 {
	
	public static boolean graphSearch(ArrayList<ArrayList<Integer>> G, int color[], int v) {
		Queue<int[]> queue = new LinkedList<int[]>();
		color[v] = 1;
		
		for(int i=0; i<G.get(v).size(); i++) {
			color[G.get(v).get(i)] = -1;
			queue.add(new int[] {G.get(v).get(i), -1});
		}
		
		while(!queue.isEmpty()) {
			int e[] = queue.poll();
			
			for(int j=0; j<G.get(e[0]).size(); j++) {
				if(color[G.get(e[0]).get(j)] == 0) {
					color[G.get(e[0]).get(j)] = e[1] == 1 ? -1 : 1;
					queue.add(new int[] { G.get(e[0]).get(j), color[G.get(e[0]).get(j)]});
				} else if (color[G.get(e[0]).get(j)] == e[1])	return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int K = Integer.parseInt(br.readLine());
		
		while(K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int color[] = new int[V];
			boolean flag = false;
			ArrayList<ArrayList<Integer>> G = new ArrayList<ArrayList<Integer>>();
			
			for(int i=0; i<V; i++)
				G.add(new ArrayList<Integer>());
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken())-1;
				int v2 = Integer.parseInt(st.nextToken())-1;
				G.get(v1).add(v2);
				G.get(v2).add(v1);
			}
			
			for(int i=0; i<V; i++) {
				if(color[i] == 0 && !graphSearch(G, color, i)) {
					flag = true;
					break;
				}
			}
			
			sb.append(flag ? "NO\n" : "YES\n");
		}
		
		System.out.print(sb);
	}
}