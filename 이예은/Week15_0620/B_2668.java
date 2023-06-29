package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class B_2668 {
	static int N, A[];
	static boolean visit[];
	static ArrayList<Integer> answer;
	
	static void search(int i, int first) {
		if(A[i] == first)	answer.add(first);
		if(!visit[A[i]]) {
			visit[A[i]] = true;
			search(A[i], first);
			visit[A[i]] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		A = new int[N+1];
		visit = new boolean[N+1];
		answer = new ArrayList<Integer>();
		
		for(int i=1; i<=N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=1; i<=N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				search(A[i], i);
				visit[i] = false;
			}
		}
		
		bw.write(answer.size()+"\n");
		for(int i : answer) {
			bw.write(i+"\n");
		}
		bw.flush();
	}
}