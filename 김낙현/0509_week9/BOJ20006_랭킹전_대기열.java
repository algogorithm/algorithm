import java.io.*;
import java.util.*;

public class BOJ20006_랭킹전_대기열 {
    public static class Room {
        int capacity;
        int upperBound;
        int lowerBound;

        PriorityQueue<User> users;

        Room(int capacity) {
            this.capacity = capacity;
            users = new PriorityQueue<>();
        }

        public boolean getIn(User user) {
            if(users.isEmpty()) {
                upperBound = user.level + 10;
                lowerBound = user.level - 10;
            }
            else if(isFull()) return false;
            else if(user.level > upperBound || user.level < lowerBound) return false;
            users.add(user);
            return true;
        }

        public boolean isFull() {
            return users.size() >= capacity;
        }

        public void printStatus() {
            StringBuilder sb = new StringBuilder().append(isFull() ? "Started" : "Waiting").append("\n");
            while(!users.isEmpty()) {
                User u = users.poll();
                sb.append(u.level).append(" ").append(u.name).append("\n");
            }
            sb.setLength(sb.length()-1);
            System.out.println(sb);
        }
    }

    public static class User implements Comparable<User> {
        String name;
        int level;

        User(int level, String name) {
            this.level = level;
            this.name = name;
        }
        public int compareTo(User o) {
            return name.compareTo(o.name);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        final int p = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());

        List<Room> roomList = new ArrayList<>();

        for(int i = 0; i < p; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            User user = new User(Integer.parseInt(st.nextToken()), st.nextToken());

            boolean isArranged = false;
            for(Room room : roomList) {
                if(isArranged = room.getIn(user)) break;
            }
            if(!isArranged) {
                Room roomCreated = new Room(m);
                roomCreated.getIn(user);
                roomList.add(roomCreated);
            }
        }

        for(Room room : roomList) {
            room.printStatus();
        }
    }
}
