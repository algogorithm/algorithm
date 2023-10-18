package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold4_2310_어드벤처게임 {
	static int n;
	static boolean check;
	static boolean[] visit;
	static ArrayList<Integer>[] list;
	static Room[] room;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			check = false;
			n = Integer.parseInt(br.readLine());
			
			if(n == 0) break;
			
			visit = new boolean[n];
			list = new ArrayList[n];
			room = new Room[n];
			
			for (int i = 0; i < n; i++) {
				list[i] = new ArrayList<>();
				st = new StringTokenizer(br.readLine());
				room[i] = new Room(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
				while(true) {
					int p = Integer.parseInt(st.nextToken());
					if(p == 0) break;
					list[i].add(p-1);
				}
			}
			
			// 이동할 수 있는 방 확인해서 이동하기
			// 확인할 때 L, E이면 가능 T면 조건 확인
			// 무한루프돌수도???????
			/*
			 * L 한정해서 방문배열 적용안하고 L 금액보다 작으면 탐색 가능하게 해주기
			 */
			int curPrice = 0;
			if(room[0].type == 'T') {
				sb.append("No\n");
				continue;
			} else if(room[0].type == 'L') {
				curPrice += room[0].price;
			} else {
				visit[0] = true;
			}
			
			move(0, curPrice);
			
			if(check) {
				sb.append("Yes\n");
			} else {
				sb.append("No\n");
			}
		}
		
		System.out.println(sb);
	}
	
	private static void move(int curRoom, int curPrice) {
		if(curRoom == n-1) {
			check = true;
			return;
		}
		
		// 다음 방 탐색
		for (int i = 0; i < list[curRoom].size(); i++) {
			int idx = list[curRoom].get(i);
			if(room[idx].type == 'T') {
				if(!visit[idx] && curPrice >= room[idx].price) {
					visit[idx] = true;
					move(idx, curPrice - room[idx].price);
					visit[idx] = false;
				}
			} else if(room[idx].type == 'L') {
				if(visit[idx] && curPrice < room[idx].price) {
					move(idx, room[idx].price);
				} else if(!visit[idx]) {
					visit[idx] = true;
					move(idx, curPrice > room[idx].price ? curPrice : room[idx].price);
					visit[idx] = false;
				}
			} else {
				if(!visit[idx]) {
					visit[idx] = true;
					move(idx, curPrice);
					visit[idx] = false;
				}
			}
		}
	}

	static class Room {
		char type;
		int price;
		
		public Room(char type, int price) {
			this.type = type;
			this.price = price;
		}
	}
}
