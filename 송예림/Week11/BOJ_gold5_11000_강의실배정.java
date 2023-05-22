package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold5_11000_강의실배정 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Lesson[] arr = new Lesson[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			arr[i] = new Lesson(S, T);
		}
		
		Arrays.sort(arr);
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.offer(arr[0].t);
		
		for (int i = 1; i < arr.length; i++) {
			// 다음 시작 시간이 맨 앞 끝나는 시간보다 크거나 같으면 빼고 넣기
			if(arr[i].s >= pq.peek()) {
				pq.poll();
			}
			pq.offer(arr[i].t);
		}
		
		System.out.println(pq.size());
		
	}

	static class Lesson implements Comparable<Lesson> {
		int s, t;
		
		public Lesson(int s, int t) {
			this.s = s;
			this.t = t;
		}
		
		public int compareTo(Lesson o) {
			if(o.s == this.s) {
				return Integer.compare(this.t, o.t);
			}
			return Integer.compare(this.s, o.s);
		}
	}
}
