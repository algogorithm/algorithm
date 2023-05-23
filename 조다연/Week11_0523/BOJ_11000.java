package week11_0523;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11000 {
	//강의실 배정
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer  st;
		
		N = Integer.parseInt(br.readLine());
		
		Lecture[] lectures = new Lecture[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			
			lectures[i] = new Lecture(S, T);
		}
		
		//시간 오름차순 정렬 (시작 시간이 같으면 끝나는 시간으로)
		Arrays.sort(lectures);

		//
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(lectures[0].end);
		
		for(int i=1; i<N; i++) {
			//우선선위 큐 가장 작은 종료 시간과 lectures[i]의 시작 시간 비교
			if(pq.peek() <= lectures[i].start) {
				pq.poll();
			}
			
			pq.add(lectures[i].end);
		}
		
		System.out.println(pq.size());
	}
	
	static class Lecture implements Comparable<Lecture> {
		
		int start;
		int end;
			
		public Lecture(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Lecture o) {
			
			if(start == o.start) {
				return end - o.end;
			}
			
			return start - o.start;
		}
		
	}

}
