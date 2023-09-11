package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class B_1941 {
	static final int N = 5, r[] = {-1,0,1,0}, c[] = {0,1,0,-1};
	static int ans;
	static char map[][];
	static ArrayList<Person> person;
	
	static class Person {
		int x, y;
		Person(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static void combination(int idx, int cnt, Person team, boolean visit[], int s) {
		if(cnt == 7) {
			if(s >= 4 && check(team))	ans++; 
			return;
		}
		
		for(int i=idx; i<person.size(); i++) {
			if(!visit[i]) {
				visit[i] = true;
				team = person.get(i);
				
				if(map[team.x][team.y] == 'S') {
					map[team.x][team.y] = 'T';
					combination(i, cnt+1, team, visit, s+1);
					map[team.x][team.y] = 'S';
				} else {
					map[team.x][team.y] = 'T';
					combination(i, cnt+1, team, visit, s);
					map[team.x][team.y] = 'Y';
				}
				
				visit[i] = false;
			}
		}
	}
	
	static boolean check(Person s) {
		Queue<Person> queue = new LinkedList<Person>();
		boolean visit[][] = new boolean[N][N];
		int cnt = 0;
		queue.add(s);
		visit[s.x][s.y] = true;
		
		while(!queue.isEmpty()) {
			Person p = queue.poll();
			cnt++;
			
			for(int d=0; d<4; d++) {
				int dr = p.x + r[d];
				int dc = p.y + c[d];
				
				if(dr < 0 || dr >= N || dc < 0 || dc >= N || visit[dr][dc] || map[dr][dc] != 'T')	continue;
				
				visit[dr][dc] = true;
				queue.add(new Person(dr, dc));
			}
		}
		
		return cnt == 7 ? true : false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[N][N];
		person = new ArrayList<Person>();
		ans = 0;
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				person.add(new Person(i, j));
			}
		}
		
		combination(0, 0, null, new boolean[person.size()], 0);
		
		System.out.print(ans);
	}
}