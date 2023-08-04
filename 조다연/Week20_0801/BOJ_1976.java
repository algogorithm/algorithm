package week20_0801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976 {
	//여행 가자
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		//부모 초기화
		parents = new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				int k = Integer.parseInt(st.nextToken());
				if(k == 1) {
					union(i,j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		boolean flag = true;
		int parent = findSet(Integer.parseInt(st.nextToken()));
		for(int i=0; i<M-1; i++) {
			int now = Integer.parseInt(st.nextToken());
			if(parent != findSet(now)) {
				flag = false;
				break;
			}
		}
		
		System.out.println(flag ? "YES" : "NO");
	}

	//부모 찾기
	private static int findSet(int a) {
		if (a == parents[a]) return a;
		
		return parents[a] = findSet(parents[a]);
	}

	//합치기
	private static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		
		if(a != b) {
			if(a < b) {
				parents[b] = a;
			} else {
				parents[a] = b;
			}
		}
	}

}
