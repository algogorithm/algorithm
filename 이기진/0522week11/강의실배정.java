package d202305;

import java.io.*;
import java.util.*;

public class 강의실배정 {

    static class TimeTable implements Comparable<TimeTable> {
        int stime, endtime;

        public TimeTable(int stime, int endtime) {
            this.stime = stime;
            this.endtime = endtime;
        }

        public int compareTo(TimeTable o) {
            if(o.stime == this.stime) {
                return Integer.compare(this.endtime, o.endtime);
            }
            return Integer.compare(this.stime, o.stime);
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        ArrayList<TimeTable> arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int stime = Integer.parseInt(st.nextToken());
            int etime = Integer.parseInt(st.nextToken());
            arr.add(new TimeTable(stime, etime));
        }
        Collections.sort(arr);

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.offer(arr.get(0).endtime);

        for (int i = 1; i < arr.size(); i++) {
            if(arr.get(i).stime >= pq.peek()) {
                pq.poll();
            }
            pq.offer(arr.get(i).endtime);
        }

        System.out.println(pq.size());

    }


}