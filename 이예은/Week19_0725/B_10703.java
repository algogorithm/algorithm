package BaekJoon;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class B_10703 {
	static int R, S;
	static char G[][];
	static ArrayList<int[]> star;
	
	public static int getCnt() {
		int min = R;
		
		for(int j=0; j<S; j++) {
			int s = -1, g = 0;
			
			for(int i=0; i<R; i++) {
				if(G[i][j] == 'X')	s = i;
				else if(G[i][j] == '#') {
					g = i-1;
					break;
				}
			}
			
			if(s != -1) {
				star.add(new int[] {j, s});
				min = Math.min(g-s, min);
			}
		}
		
		return min;
	}
	
	public static void move(int min) {
		for(int[] s : star) {
			for(int i = s[1]; i>=0; i--) {
				if(G[i][s[0]] == 'X') {
					G[i+min][s[0]] = 'X';
					G[i][s[0]] = '.';
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = new char[R][S];
		star = new ArrayList<int[]>();
		
		for(int i=0; i<R; i++) {
			G[i] = br.readLine().toCharArray();
		}
		
		int min = getCnt();
		move(min);
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<S; j++)
				bw.write(G[i][j]);
			bw.write("\n");
		}
		bw.flush();
	}
}