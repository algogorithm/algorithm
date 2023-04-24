package week7_0425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14218 {
	//그래프 탐색 2
	//다익스트라 : 한 노드에서 모든 노드로 가는 최단거리 
	//최단거리 저장배열 + 우선순위 큐로 구현
	static int N, M, Q;
	static int[] distance;
	static int INF = Integer.MAX_VALUE;
	static int[][] city;
	static boolean[] v;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//도시의 개수 N, 도로의 개수 M
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//인접행렬
		city = new int[N+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			//도시 두개 -> 도로 (서로 연결)
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			//가중치 1
			city[a][b] = 1;
			city[b][a] = 1;
		}
		
		//도로 정비 계획에 들어가 있는 도로의 수 Q
		Q = Integer.parseInt(br.readLine());
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			//도시 두개 -> 정비된 도로 (서로 연결)
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			//가중치 1
			city[a][b] = 1;
			city[b][a] = 1;
			
			//최단 거리 저장 배열
			distance = new int[N+1];
			Arrays.fill(distance, INF);
			
			//수도에서 시작, 수도는 1번
			//도로 정비될 때마다 최소 몇 개의 도시 방문하는지 출력
			//가중치가 1인 최단 경로 다익스트라
			bfs(1);
			
			answer();
		}
	}

	private static void bfs(int idx) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(idx,0));
		v = new boolean[N+1];
		distance[idx] = 0; //출발지점 0
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			//비용이 최소값이면 갱신
			if(distance[p.idx] > p.cost) {
				distance[p.idx] = p.cost;
			}
			v[p.idx] = true;

			//city[idx]와 연결된 정점 확인
			for(int i=1; i<city[p.idx].length; i++) {
				//도로가 이어져 있고 방문하지 않았으면
				if(city[p.idx][i] == 1 && !v[i]) {
					q.add(new Point(i,p.cost+1));
					v[i]= true;
				}
			}
		}
	}

	private static void answer() {
		for(int j=1; j<=N; j++) {
			if(distance[j] == INF) {
				System.out.print("-1 ");
			} else {
				System.out.print(distance[j]+" ");
			}
		}
		System.out.println();
	}

	static class Point {
		int idx; 
		int cost;
		
		public Point(int idx, int cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}
	}
}
