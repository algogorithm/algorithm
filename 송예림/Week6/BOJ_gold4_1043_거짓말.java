package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold4_1043_거짓말 {
	static int N, M, result = 0;
	static boolean[] tArr;
	static int[] parent;
	static ArrayList<Integer>[] party;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tArr = new boolean[N+1];
		parent = new int[N+1];
		party = new ArrayList[M];

		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			// 진실 아는 사람
			tArr[Integer.parseInt(st.nextToken())] = true;
		}

		int pre = 0;
		// 파티원 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			party[i] = new ArrayList<Integer>();

			// 처음 사람은 그냥 넣기 (root)
			if(p > 0) {
				pre = Integer.parseInt(st.nextToken());
				party[i].add(pre);
			}

			// 같은 파티끼리 합치기 -> 부모 무조건 작은애들임
			for (int j = 1; j < p; j++) {
				int a = Integer.parseInt(st.nextToken());
				party[i].add(a);
				union(pre, a);
				pre = a;
			}
		}


		// 진실 아는 사람의 주변 사람들도 true로 바꿔주기 -> 부모가 같으면 ok
		// 부모 비교할 때, parent[j] == parent[i] 가 아니라 find 함수로 부모를 찾아줘야함!!!!!
		for (int i = 1; i < tArr.length; i++) {
			if(tArr[i]) {
				for (int j = 1; j < tArr.length; j++) {
					if(find(j) == find(i)) {
						tArr[j] = true;
					}
				}
			}
		}

		for (int i = 1; i < parent.length; i++) {
			System.out.println(i + " : " + tArr[i] + " " + parent[i]);
		}


		// 파티에 진실 아는 사람 있는지 체크
		for (int i = 0; i < party.length; i++) {
			boolean chk = false;
			for (int j = 0; j < party[i].size(); j++) {
				if(tArr[party[i].get(j)]) {
					chk = true;
					System.out.println(i);
					break;
				}
			}
			if(!chk)
				result++;
		}

		System.out.println(result);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
//		if(a != b)
//			parent[b] = a;
		if(a < b) {
			parent[b] = a;
		} else if(a > b) {
			parent[a] = b;
		}
	}

	private static int find(int a) {
		if(parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);
	}

}
