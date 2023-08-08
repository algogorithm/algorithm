package BaekJoon;

import java.io.*;
import java.util.*;

public class B_14719 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int ans = 0;
		boolean visit[][] = new boolean[H][W];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) {
			int h = Integer.parseInt(st.nextToken());
			for(int j=H-h; j<H; j++)
				visit[j][i] = true;
		}
		
		for(int i=0; i<H; i++) {
			boolean block = false;			
			int cnt = 0;
			
			for(int j=0; j<W; j++) {
				if(visit[i][j] && !block)	block = true;
				else if(!visit[i][j] && block)	cnt++;
				else if(visit[i][j] && block && cnt > 0) {
					ans += cnt;
					cnt = 0;
				}
			}
		}
		
		System.out.print(ans);
	}
}