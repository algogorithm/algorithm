package Week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15683_감시 {
	public static class CCTV{
		int r;
		int c;
		int type;
		boolean[][] map;
		
		public CCTV(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
		
		CCTV findTop() {
			for(int i=this.r-1; i>=0; i--) {
				if(this.map[i][this.c]) break;
				
				this.map[i][this.c] = true;
			}
			return this;
		}
		CCTV findDown() {
			for(int i=this.r+1; i<this.map.length; i++) {
				if(map[i][this.c]) break;
				
				this.map[i][this.c] = true;
			}
			return this;
		}
		CCTV findLeft() {
			for(int i=this.c-1; i>=0; i--) {
				if(this.map[this.r][i]) break;
				
				this.map[this.r][i] = true;
			}
			return this;
		}
		CCTV findRight() {
			for(int i=this.c+1; i<this.map[0].length; i++) {
				if(this.map[this.r][i]) break;
				
				this.map[this.r][i] = true;
			}
			return this;
		}
	}
	static List<CCTV> CCTVS;
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[N][M];
		CCTVS = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int input = Integer.parseInt(st.nextToken());
				if(input > 0 && input < 6) CCTVS.add(new CCTV(i, j, input));
				else if(input == 6) map[i][j] = true;
			}
		}
		
		selectDirectionBfs(map);
		
		
	}
	private static void selectDirectionBfs(boolean[][] map) {
		Queue<CCTV> q = new LinkedList<>();
		q.add(null);

		while(!q.isEmpty()) {
			
		}
		
		
	}
	private static void calculateCCTV(CCTV cctv, int type, int direction) {
		if(type==1) {
			switch(direction) {
			case 1:
				cctv.findTop();
				break;
			case 2:
				cctv.findDown();
				break;
			case 3:
				cctv.findLeft();
				break;
			case 4:
				cctv.findRight();
				break;
			}
		} 
		else if(type == 2) {
			switch(direction%2) {
			case 0:
				cctv.findLeft().findRight();
				break;
			case 1:
				cctv.findTop().findDown();
				break;
			}
		} 
		else if(type == 3) {
			switch(direction) {
			case 1:
				cctv.findTop().findRight();
				break;
			case 2:
				cctv.findRight().findDown();
				break;
			case 3:
				cctv.findDown().findLeft();
				break;
			case 4:
				cctv.findLeft().findTop();
				break;
			}
		} 
		else if(type == 4) {
			switch(direction) {
			case 1:
				cctv.findTop().findRight().findDown();
				break;
			case 2:
				cctv.findRight().findDown().findDown();
				break;
			case 3:
				cctv.findDown().findLeft().findTop();
				break;
			case 4:
				cctv.findLeft().findTop().findRight();
				break;
			}
		} 
		else if(type == 5) {
			cctv.findTop().findDown().findLeft().findRight();
		}
		
	}
	private static boolean[][] copyMap(boolean[][] map) {
		boolean[][] tmp = new boolean[map.length][map[0].length];
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		
		return tmp;
	}

	
}
