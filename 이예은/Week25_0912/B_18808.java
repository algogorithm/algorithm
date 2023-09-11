package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_18808 {
	static int N, M, K, ans;
	static boolean notebook[][];
	static ArrayList<Sticker> sticker;
	
	static class Sticker {
		int r, c;
		boolean paper[][];
		
		Sticker(int r, int c, boolean paper[][]) {
			this.r = r;
			this.c = c;
			this.paper = paper;
		}
	}
	
	static boolean search(Sticker s) {
		for(int i=0; i<=N-s.r; i++) {
			for(int j=0; j<=M-s.c; j++) {
				if(check(s, i, j)) {
					attach(s, i, j);
					return true;
				}
			}
		}
		
		return false;
	}
	
	static boolean check(Sticker s, int x, int y) {
		for(int i=0; i<s.r; i++) {
			for(int j=0; j<s.c; j++) {
				if(s.paper[i][j] && notebook[x+i][y+j])	return false;
			}
		}
		
		return true;
	}
	
	static Sticker rotate(Sticker s) {
		int nr = s.c, nc = s.r;
		boolean npaper[][] = new boolean[nr][nc];
		
		for(int r=0; r<nr; r++) {
			for(int c=0; c<nc; c++) {
				npaper[r][c] = s.paper[nc-1-c][r];
			}
		}
		
		return new Sticker(nr, nc, npaper);
	}
	
	static void attach(Sticker s, int x, int y) {
		for(int i=0; i<s.r; i++) {
			for(int j=0; j<s.c; j++) {
				if(s.paper[i][j]) {
					notebook[x+i][y+j] = true;
					ans++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = 0;
		notebook = new boolean[N][M];
		sticker = new ArrayList<Sticker>();
		
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			boolean paper[][] = new boolean[R][C];

			for(int r=0; r<R; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<C; c++) {
					if(Integer.parseInt(st.nextToken()) == 1)	paper[r][c] = true;
				}
			}
			
			sticker.add(new Sticker(R, C, paper));
		}

		for(Sticker s : sticker) {
			if(search(s))	continue;
			
			for(int i=0; i<3; i++) {
				s = rotate(s);
				if(search(s))	break;
			}
		}
		
		System.out.print(ans);
	}
}