package BaekJoon;

import java.io.*;
import java.util.*;

public class B_20006 {

	static class Player implements Comparable<Player> {
		int l;
		String n;

		Player(int l, String n) {
			this.l = l;
			this.n = n;
		}

		@Override
		public int compareTo(Player p) {
			return this.n.compareTo(p.n);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Player>> room = new ArrayList<ArrayList<Player>>();

		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			String n = st.nextToken();
			boolean flag = false;

			for (int j = 0; j < room.size(); j++) {
				if (room.get(j).size() < M) {
					int level = room.get(j).get(0).l;
					if (l >= level - 10 && l <= level + 10) {
						room.get(j).add(new Player(l, n));
						flag = true;
						break;
					}
				}
			}

			if (!flag) {
				room.add(new ArrayList<Player>());
				room.get(room.size() - 1).add(new Player(l, n));
			}
		}

		for (int i = 0; i < room.size(); i++) {
			ArrayList<Player> r = room.get(i);
			
			if (r.size() == M)	bw.write("Started!\n");
			else	bw.write("Waiting!\n");

			Collections.sort(r);

			for (int j = 0; j < r.size(); j++)
				bw.write(r.get(j).l + " " + r.get(j).n + "\n");
		}
		
		bw.flush();
		
	}
}