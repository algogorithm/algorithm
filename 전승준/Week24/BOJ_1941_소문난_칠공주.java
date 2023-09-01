package Week24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1941_소문난_칠공주 {
	static char[][] people;
	static int answer;
	static int[] drc = {0,-1,0,1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		people = new char[5][5];
		
		for(int i=0; i<5; ++i) {
			String str = br.readLine();
			for(int j=0; j<5; ++j) {
				people[i][j] = str.charAt(j);
			}
		}
		
		combination(25, 7, 0, new boolean[25]);
		
		System.out.println(answer);

	}
	private static void combination(int n, int r, int start, boolean[] comb) {
		if(r == 0) {
			connectCheck(comb);
			return;
		}
		
		for(int i=start; i<25; ++i) {
			comb[i] = true;
			combination(n, r-1, i+1, comb);
			comb[i] = false;
		}
		
	}
	private static void connectCheck(boolean[] comb) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] check = new boolean[25];
		
		for(int i=0; i<25; ++i) {
			if(comb[i]) {
				queue.offer(i);
				break;
			}
		}

		int S = 0;
		int connected = 0;
		
		while(!queue.isEmpty()) {
			int poll = queue.poll();
			
			for(int d=0; d<4; ++d) {
				int nr = poll / 5 + drc[d];
				int nc = poll % 5 + drc[d+1];
				int nrc = nr * 5 + nc;
				
				if(nr>=0 && nr<5 && nc>=0 && nc<5 && comb[nrc] && !check[nrc]) {
					check[nrc] = true;
					queue.offer(nrc);
					++connected;
					
					if(people[nr][nc] == 'S') ++S;
				}
			}
		}
		
		if(connected == 7 && S >= 4) ++answer;
		
	}
}
