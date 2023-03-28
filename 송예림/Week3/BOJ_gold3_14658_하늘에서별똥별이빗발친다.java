package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold3_14658_하늘에서별똥별이빗발친다 {
	static ArrayList<Node> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new Node(r, c));
		}
		
		int result = list.size();
		
		for (Node a : list) {
			for (Node b : list) {
				int cnt = list.size();
				for (Node tmp : list) {
					if(tmp.r >= a.r && tmp.r <= a.r+L && tmp.c >= b.c && tmp.c <= b.c+L) {
						cnt--;
					}
				}
				result = Math.min(result, cnt);
			}
		}
		System.out.println(result);
	}
	
	static class Node{
		int r, c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}