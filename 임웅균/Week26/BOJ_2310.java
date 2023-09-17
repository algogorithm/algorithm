package Week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2310 {

    static int n;
    static List<Room> rooms;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            // 금액으로 방문체크
            visited = new boolean[n][501];
            rooms = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                char type = st.nextToken().charAt(0);
                int price = Integer.parseInt(st.nextToken());
                List<Integer> room = new LinkedList<>();
                while (st.countTokens() > 1) {
                    room.add(Integer.parseInt(st.nextToken()) - 1);
                }
                rooms.add(new Room(type, price, room));
            }
            System.out.println(solve(0, 0) ? "Yes" : "No");
        }
    }

    private static boolean solve(int startIdx, int startMoney) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startIdx, startMoney});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int idx = current[0];
            int money = current[1];
            Room room = rooms.get(idx);

            if (idx == n - 1) {
                if (room.type == 'T') {
                    money -= room.money;
                    if (money < 0) {
                        continue;
                    }
                }
                return true;
            }

            if (room.type == 'T') {
                money -= room.money;
                if (money < 0) {
                    continue;
                }
            } else if (room.type == 'L') {
                if (money < room.money) {
                    money = room.money;
                }
            }

            for (int nextIdx : room.connectedRooms) {
                if (!visited[nextIdx][money]) {
                    visited[nextIdx][money] = true;
                    queue.offer(new int[]{nextIdx, money});
                }
            }
        }

        return false;
    }

    static class Room {
        char type;
        int money;
        List<Integer> connectedRooms;

        public Room(char type, int money, List<Integer> connectedRooms) {
            this.type = type;
            this.money = money;
            this.connectedRooms = connectedRooms;
        }
    }

//    private static boolean solve(int idx, int money) {
//        Room room = rooms.get(idx);
//        int tempMoney = money;
//        if (idx == n - 1) {
//            // 여기서도 계산 한번 해줘서 갈 수 있는지 판단해야한다.
//            // 이거 안해줘서 1번이 계속 틀렸다.
//            if (room.type == 'T') {
//                tempMoney -= room.money;
//                if (tempMoney < 0) {
//                    return false;
//                }
//            }
//            return true;
//        }
//        if (room.type == 'T') {
//            tempMoney -= room.money;
//            if (tempMoney < 0) {
//                return false;
//            }
//        } else if (room.type == 'L') {
//            if (tempMoney < room.money) {
//                tempMoney = room.money;
//            }
//        }
//        // 다음에 갈곳 지정
//        for (int nextIdx : room.connectedRooms) {
//            if (visited[nextIdx][tempMoney] == false) {  // 이전에 이 금액으로 방문하지 않았다면
//                visited[nextIdx][tempMoney] = true;  // 방문 체크
//                if (solve(nextIdx, tempMoney)) {  // 다음 방으로 이동
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
