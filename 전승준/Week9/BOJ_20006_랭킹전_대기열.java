package Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20006_랭킹전_대기열 {
	static class Player implements Comparable<Player> {
		int level;
		String nickName;
		
		public Player(int level, String nickName) {
			this.level = level;
			this.nickName = nickName;
		}

		@Override
		public int compareTo(Player o) {
			return this.nickName.compareTo(o.nickName);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int players = Integer.parseInt(st.nextToken());
		int capacity = Integer.parseInt(st.nextToken());
		List<List<Player>> list = new LinkedList<>();
		
		for(int p=0; p<players; p++) {
			st = new StringTokenizer(br.readLine());
			int inLevel = Integer.parseInt(st.nextToken());
			String inNickName = st.nextToken();
			boolean isAdded = false;
			
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).size() == capacity) continue;
				
				int entLevel = list.get(i).get(0).level;
				
				if(entLevel - 10 <= inLevel && inLevel <= entLevel + 10) {
					list.get(i).add(new Player(inLevel, inNickName));
					isAdded = true;
					break;
				}
			}
			if(!isAdded) {
				list.add(new LinkedList<>());
				list.get(list.size()-1).add(new Player(inLevel, inNickName));
			}
		}
		
		for(int i=0; i<list.size(); i++) {
			Collections.sort(list.get(i));
			
			if(list.get(i).size() == capacity) System.out.println("Started!");
			else System.out.println("Waiting!");
			
			for(int j=0; j<list.get(i).size(); j++) {
				Player tmp = list.get(i).get(j);
				System.out.println(tmp.level+" "+tmp.nickName);
			}
		}

	}

}
