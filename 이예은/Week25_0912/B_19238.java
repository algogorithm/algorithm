package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_19238 {
	static final int r[] = {-1,0,1,0}, c[] = {0,1,0,-1};
	static int N, M, K, map[][], tx, ty;
	static Person person[];
	
	static class Point implements Comparable<Point>{
		int x, y, t;
		
		Point(int x, int y, int t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}
		
		@Override
		public int compareTo(Point p) {
			return this.t - p.t;
		}
	}
	
	static class Person {
		int sx, sy, dx, dy;
		Person(int sx, int sy, int dx, int dy) {
			this.sx = sx;
			this.sy = sy;
			this.dx = dx;
			this.dy = dy;
		}
	}
	
	static Point getPerson() {
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		boolean visit[][] = new boolean[N][N];
		Point point = new Point(-1, -1, Integer.MAX_VALUE);
		queue.add(new Point(tx, ty, 0));
		visit[tx][ty] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(map[p.x][p.y] > 1) {
				if(point.t > p.t)	point = p;
				else if(point.t < p.t)	break;
				else {
					if(point.x > p.x || (point.x == p.x && point.y > p.y))	point = p;
				}
			}
			
			for(int d=0; d<4; d++) {
				int dr = p.x + r[d];
				int dc = p.y + c[d];
				
				if(dr < 0 || dr >= N || dc < 0 || dc >= N || visit[dr][dc] || map[dr][dc] == 1)	continue;
				visit[dr][dc] = true;
				queue.add(new Point(dr, dc, p.t+1));
			}
		}
		
		return point;
	}
	
	static Point outPerson(int dx, int dy) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean visit[][] = new boolean[N][N];
		Point point = null;
		queue.add(new Point(tx, ty, 0));
		visit[tx][ty] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.x == dx && p.y == dy) {
				point = p;
				break;
			}
			
			for(int d=0; d<4; d++) {
				int dr = p.x + r[d];
				int dc = p.y + c[d];
				
				if(dr < 0 || dr >= N || dc < 0 || dc >= N || visit[dr][dc] || map[dr][dc] == 1)	continue;
				visit[dr][dc] = true;
				queue.add(new Point(dr, dc, p.t+1));
			}
		}
		
		return point;
	}
	
	static boolean moveTaxi(Point p) {
		K -= p.t;
		tx = p.x;
		ty = p.y;
		return K >= 0 ? true : false;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		person = new Person[M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		tx = Integer.parseInt(st.nextToken())-1;
		ty = Integer.parseInt(st.nextToken())-1;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			person[i] = new Person(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
			map[person[i].sx][person[i].sy] = i+2;
		}
		
		while(M > 0) {
			Point point = getPerson();
			if(point.x == -1 || !moveTaxi(point))	break;
			
			int idx = map[point.x][point.y]-2;
			map[point.x][point.y] = 0;

			point = outPerson(person[idx].dx, person[idx].dy);
			if(point == null || !moveTaxi(point))	break;

			K += point.t*2;
			M--;
		}
		
		System.out.print(M == 0 ? K : -1);
	}
}