package Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11000_강의실_배정 {
	static class Lesson implements Comparable<Lesson> {
		int start;
		int end;
		
		public Lesson(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Lesson o) {
			return this.start - o.start;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Lesson[] lessons = new Lesson[N];
		PriorityQueue<Integer> position = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			lessons[i] = new Lesson(start, end);
		}
		
		Arrays.sort(lessons);
		position.add(lessons[0].end);
		
		for(int i=1; i<lessons.length; i++) {
			if(position.peek() <= lessons[i].start) {
				position.poll();
			}
			position.add(lessons[i].end);
		}
		
		System.out.println(position.size());
		
	}

}
