package Week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1043_거짓말 {
	static int[] PEOPLE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PEOPLE = new int[N+1];
		for(int i=1; i<PEOPLE.length; i++) {
			PEOPLE[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		int knowPersons = Integer.parseInt(st.nextToken());
		for(int i=0; i<knowPersons; i++) {
			merge(0, Integer.parseInt(st.nextToken()));
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int person = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<person; j++) {
				find(Integer.parseInt(st.nextToken()));
			}
		}
		
	}
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		PEOPLE[y] = x;
	}
	private static int find(int x) {
		if(x == PEOPLE[x]) return x;
		else return find(PEOPLE[x]);
	}

}
