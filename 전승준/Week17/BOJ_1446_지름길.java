package Week17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1446_지름길 {
	static class Road implements Comparable<Road> {
		int s;
		int e;
		int w;

		public Road(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Road o) {
			if (this.s == o.s)
				return this.e - o.e;
			return this.s - o.s;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		List<Road> list = new ArrayList<>();

		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			if (e > D)
				continue;

			if (e - s <= w)
				continue;
			list.add(new Road(s, e, w));
		}
		Collections.sort(list);

		int idx = 0, move = 0;
		int[] dist = new int[10001];
		Arrays.fill(dist, 10001);
		dist[0] = 0;
		// 다익스트라
		while (move < D) {
			if (idx < list.size()) {
				Road r = list.get(idx);
				if (move == r.s) {
					dist[r.e] = Math.min(dist[move] + r.w, dist[r.e]);
					idx++;
				} else {
					dist[move + 1] = Math.min(dist[move + 1], dist[move] + 1);
					move++;
				}
			} else {
				dist[move + 1] = Math.min(dist[move + 1], dist[move] + 1);
				move++;
			}
		}
		System.out.println(dist[D]);

	}

}
