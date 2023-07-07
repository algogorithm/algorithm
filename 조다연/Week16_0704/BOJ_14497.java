package week16_0704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_14497 {
	//주난의 난
	static int N, M;
	static int x1, y1, x2, y2;
	static char[][] map;
	//					상 하 좌 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1}; 

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		//주난
		x1 = Integer.parseInt(st.nextToken())-1;
		y1 = Integer.parseInt(st.nextToken())-1;
		//범인
		x2 = Integer.parseInt(st.nextToken())-1;
		y2 = Integer.parseInt(st.nextToken())-1;
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		System.out.println(jump());
	}

	// 0-1 BFS : 가중치가 0과 1로만 주어진 그래프에서 최단 경로를 찾고자 할 때
	private static int jump() {
		Deque<int[]> dq = new LinkedList<int[]>();
		boolean[][] v = new boolean[N][M];
		v[x1][y1] = true;
		dq.add(new int[] {x1, y1, 0});
		
		while(!dq.isEmpty()) {
			int[] a = dq.poll();
			
			//초코바의 위치면 가중치 return
			if(a[0] == x2 && a[1] == y2) return a[2];
			
			//상하좌우 탐색
			for(int d=0; d<4; d++) {
				int nr = a[0]+dr[d];
				int nc = a[1]+dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || v[nr][nc]) continue;
				
				v[nr][nc] = true;
				
				// 현재 노드까지 소비된 비용 + 그 노드를 향하는 가중치 < 그 노드까지 가는데 소비된 비용 이면 소비된 비용을 갱신
				//노드를 향하는 가중치가 0이면 덱의 맨 앞에, 가중치가 1이면 덱의 맨 뒤에 추가
				
				//다음 지점이 0이라면, 한 번의 점프로 도달 가능하므로 현재 지점까지의 점프 횟수로 갱신
				if(map[nr][nc] == '0') dq.addFirst(new int[] {nr, nc, a[2]});
				//다음 지점이 1이라면, 파동이 멈추므로 (현재 지점까지의 점프 횟수+1)로 갱신
				else dq.addLast(new int[] {nr, nc, a[2]+1});
			}
		}
		
		return 0;
	}

}
