import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11000_강의실_배정 {
    static class ClassRoom implements Comparable<ClassRoom> {
        Class onClass;

        ClassRoom(Class onClass) {
            this.onClass = onClass;
        }

        public boolean isAvailable(Class nextClass) {
            return onClass.finishAt <= nextClass.startAt;
        }

        @Override
        public int compareTo(ClassRoom o) {
            return this.onClass.finishAt - o.onClass.finishAt;
        }
    }

    static class Class implements Comparable<Class> {
        int startAt;
        int finishAt;

        public Class(int startAt, int finishAt) {
            this.startAt = startAt;
            this.finishAt = finishAt;
        }

        @Override
        public int compareTo(Class o) {
            return this.startAt - o.startAt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Class> classes = new PriorityQueue<>();
        PriorityQueue<ClassRoom> arrangedClassRoom = new PriorityQueue<>();

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            classes.add(new Class(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        arrangedClassRoom.add(new ClassRoom(classes.poll()));

        int usedClassRoomCnt = 1;
        while (!classes.isEmpty()) {
            Class nextClass = classes.poll();
            ClassRoom fastestFinishClassRoom = arrangedClassRoom.peek();
            if (fastestFinishClassRoom.isAvailable(nextClass)) {
                arrangedClassRoom.poll();
            } else {
                ++usedClassRoomCnt;
            }
            arrangedClassRoom.add(new ClassRoom(nextClass));
        }

        System.out.println(usedClassRoomCnt);
    }
}
