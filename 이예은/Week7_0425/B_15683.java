package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_15683 {
	static int N, M, S[][], ans = 100;
	static ArrayList<CCTV> list;
	static int r[] = {-1,0,1,0};
	static int c[] = {0,1,0,-1};
	
	static class CCTV{
		int x, y, n;
		
		CCTV(int x, int y, int n){
			this.x = x;
			this.y = y;
			this.n = n;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new int[N][M];
		list = new ArrayList<CCTV>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
				if(S[i][j] > 0 && S[i][j] < 6)	list.add(new CCTV(i, j, S[i][j]));
			}
		}
		
		search(0);
		System.out.print(ans);
	}
	
	public static void search(int idx) {
		if(idx == list.size()) {
			ans = Math.min(ans, count());
			return;
		}
		
		CCTV ctv = list.get(idx);
		switch(ctv.n) {
			case 1 :
				for(int i=0; i<4; i++) {
					change(ctv.x, ctv.y, i);
					search(idx+1);
					back(ctv.x, ctv.y, i);
				}
				break;
				
			case 2 : 
				for(int i=0; i<2; i++) {
					change(ctv.x, ctv.y, i);
					change(ctv.x, ctv.y, i+2);
					search(idx+1);
					back(ctv.x, ctv.y, i);
					back(ctv.x, ctv.y, i+2);
				}
				break;
			
			case 3 :
				for(int i=0; i<4; i++) {
					change(ctv.x, ctv.y, i);
					change(ctv.x, ctv.y, (i+1)%4);
					search(idx+1);
					back(ctv.x, ctv.y, i);
					back(ctv.x, ctv.y, (i+1)%4);
				}
				break;
			
			case 4 :
				for(int i=0; i<4; i++) {
					change(ctv.x, ctv.y, i);
					change(ctv.x, ctv.y, (i+1)%4);
					change(ctv.x, ctv.y, (i+2)%4);
					search(idx+1);
					back(ctv.x, ctv.y, i);
					back(ctv.x, ctv.y, (i+1)%4);
					back(ctv.x, ctv.y, (i+2)%4);
				}
				break;
				
			case 5 :
				for(int i=0; i<4; i++)
					change(ctv.x, ctv.y, i);
				
				search(idx+1);
				
				for(int i=0; i<4; i++)
					back(ctv.x, ctv.y, i);
				break;
		}
	}
	
	public static int count() {
		int cnt = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(S[i][j] == 0)	cnt++;
			}
		}
		
		return cnt;
	}
	
	public static void change(int x, int y, int d) {
		for(int i = 1; i < Math.max(N, M); i++) {
			int dr = x + r[d]*i;
			int dc = y + c[d]*i;
			
			if(dr < 0 || dr >= N || dc < 0 || dc >= M || S[dr][dc] == 6)	break;
			else if(S[dr][dc] <= 0)	S[dr][dc]--;
		}
	}
	
	public static void back(int x, int y, int d) {
		for(int i = 1; i < Math.max(N, M); i++) {
			int dr = x + r[d]*i;
			int dc = y + c[d]*i;
			
			if(dr < 0 || dr >= N || dc < 0 || dc >= M || S[dr][dc] == 6)	break;
			else if(S[dr][dc] < 0)	S[dr][dc]++;
		}
	}
}