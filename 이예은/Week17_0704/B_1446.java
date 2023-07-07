package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Road implements Comparable<Road>{
	int start, end, length;
	
	Road(int start, int end, int length) {
		this.start = start;
		this.end = end;
		this.length = length;
	}
	
	@Override
	public int compareTo(Road r) {
		if(this.start == r.start)	return this.end - r.end;
		return this.start - r.start;
	}
}

public class B_1446 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		ArrayList<Road> list = new ArrayList<Road>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			
			if(end > D || end - start <= length)	continue;
			list.add(new Road(start, end, length));
		}
		
		Collections.sort(list);
		
		int current = 0, idx = 0;
		int dist[] = new int[D+1];
		Arrays.fill(dist, 10001);
		dist[0] = 0;
		
		while(current < D) {
			if(idx < list.size()) {
				Road road = list.get(idx);
				if(current == road.start) {
					dist[road.end] = Math.min(dist[current] + road.length, dist[road.end]);
					idx++;
				} else {
					dist[current + 1] = Math.min(dist[current + 1], dist[current] + 1);
					current++;
				}
			} else {
				dist[current + 1] = Math.min(dist[current + 1], dist[current] + 1);
				current++;
			}
		}
		
		System.out.print(dist[D]);
	}
}