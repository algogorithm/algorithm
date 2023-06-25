package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_15684 {
	static int N, M, H, ans;
	static boolean ladder[][];
	
	static void addLine(int cnt, int h, int n) {
		if(n == N-1) {
			h++;
			n = 0;
		}
		if(h == H)	return;
		
		if(cnt < 3 && !ladder[h][n] && check(h, n)) {
			ladder[h][n] = true;
			if(game())	ans = Math.min(ans, cnt+1);

			addLine(cnt+1, h, n+1);
			ladder[h][n] = false;
		}
		addLine(cnt, h, n+1);
	}
	
	static boolean check(int h, int n) {
		if(n-1 >= 0 && ladder[h][n-1])	return false;
		if(n+1 < N-1 && ladder[h][n+1])	return false;
		return true;
	}

	static boolean game() {
		boolean flag = true;
		for(int i=0; i<N; i++) {
			if(getNum(i) != i) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	static void print() {
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				
			}
		}
	}
	
	static int getNum(int n) {
		for(int i=0; i<H; i++) {
			if(n < N && ladder[i][n])	n++;
			else if(n-1 >= 0 && ladder[i][n-1])	n--;
		}
		return n;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		ladder = new boolean[H][N];
		ans = Integer.MAX_VALUE;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			ladder[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
		}
		
		if(game())	ans = 0;
		else	addLine(0, 0, 0);
		
		System.out.print(ans > 3 ? -1 : ans);
	}
}