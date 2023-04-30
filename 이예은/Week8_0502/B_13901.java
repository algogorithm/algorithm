package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_13901 {
	static int r[] = {-1,1,0,0};
	static int c[] = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		boolean room[][] = new boolean[R][C];
		int D[] = new int[4];
		int t = 0, cnt = 0;
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			room[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}
		
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		room[sr][sc] = true;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			D[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		while(true) {
			int dr = sr + r[D[t%4]];
			int dc = sc + c[D[t%4]];
			
			if(dr >= 0 && dr < R && dc >= 0 && dc < C && !room[dr][dc])	{
				room[dr][dc] = true;
				sr = dr;
				sc = dc;
				cnt = 0;
			} else {
				cnt++;
				t++;
			}
			
			if(cnt == 4)	break;
		}
		
		System.out.print(sr+" "+sc);
	}
}