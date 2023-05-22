package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_11000 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> room = new PriorityQueue<Integer>();
		int N = Integer.parseInt(br.readLine());
		int lesson[][] = new int[N][2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lesson[i][0] = Integer.parseInt(st.nextToken());
			lesson[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(lesson, (a, b) -> a[0] - b[0]);
		
		for(int[] l : lesson) {
			if(!room.isEmpty() && room.peek() <= l[0])	room.poll();
			room.add(l[1]);
		}
		
		System.out.print(room.size());
	}
}