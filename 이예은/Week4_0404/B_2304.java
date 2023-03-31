package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2304 {
	
	static class Block implements Comparable<Block> {
		int w, h;
		Block(int w, int h) {
			this.w = w;
			this.h = h;
		}
		
		@Override
		public int compareTo(Block b) {
			return this.w - b.w;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		Block[] list = new Block[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			list[i] = new Block(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(list);
		Block max = list[0];
		int idx = 0;
				
		for(int i=1; i<N; i++) {
			if(max.h <= list[i].h) {
				answer += max.h * (list[i].w - max.w);
				max = list[i];
				idx = i;
			}
		}
		
		max = list[N-1];
		for(int i=N-2; i>=idx; i--) {
			if(max.h <= list[i].h) {
				answer += max.h * (max.w - list[i].w);
				max = list[i];
			}
		}
		
		System.out.print(answer+max.h);
	}
}