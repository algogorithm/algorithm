package d202305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 랭킹전 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Room> rooms = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int lv = Integer.parseInt(st.nextToken());
            String id = st.nextToken();

            boolean flag = false;
            for(Room room : rooms) {
                if(room.users.size() == m) continue;

                if(room.users.get(0).level + 10 >= lv && lv >= room.users.get(0).level - 10) {
                    room.users.add(new User(lv, id));
                    flag = true;
                    break;
                }
            }

            if(flag == false) {
                Room room = new Room();
                room.users.add(new User(lv, id));
                rooms.add(room);
            }
        }

        for (Room room : rooms) {
            Collections.sort(room.users);
            if(room.users.size() == m) {
                System.out.println("Started!");
                for(int i = 0; i < room.users.size(); i++) {
                    System.out.println(room.users.get(i).level + " " + room.users.get(i).id);
                }
            } else {
                System.out.println("Waiting!");
                for(int i = 0; i < room.users.size(); i++) {
                    System.out.println(room.users.get(i).level + " " + room.users.get(i).id);
                }
            }
        }
    }

    static class Room {
        ArrayList<User> users = new ArrayList<>();
    }

    static class User implements Comparable<User>{
        int level;
        String id;

        public User(int level, String id) {
            this.level = level;
            this.id = id;
        }

        @Override
        public int compareTo(User o) {
            return this.id.compareTo(o.id);
        }
    }
}