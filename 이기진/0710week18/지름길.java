package d202307;

import java.util.*;

public class 지름길 {
    static int n, d;

    static class Road implements Comparable<Road>{
        int y, cost;
        Road(int y, int cost){
            this.y = y;
            this.cost = cost;
        }

        public int compareTo(Road o){
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        d = sc.nextInt();

        // 거리 배열 세팅
        int[] distacne = new int[d + 1];
        for(int i = 0; i < d + 1; i++){
            distacne[i] = Integer.MAX_VALUE;
        }

        // 그래프 세팅
        ArrayList<Road>[] list = new ArrayList[d + 1];
        for(int i = 0; i < d + 1; i++) list[i] = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int x, y, c;
            x = sc.nextInt();
            y = sc.nextInt();
            c = sc.nextInt();
            if(x > d || y > d) continue;
            Road node = new Road(y, c);
            list[x].add(node);
        }
        for(int i = 0; i < d; i++){
            list[i].add(new Road(i + 1, 1));
        }

        // 다익스트라
        PriorityQueue<Road> pq = new PriorityQueue();
        pq.offer(new Road(0, 0));
        distacne[0] = 0;
        while(!pq.isEmpty()){
            Road now = pq.poll();
            if(distacne[now.y] < now.cost) continue;
            for(Road o : list[now.y]){
                int cost = o.cost + now.cost;
                if(distacne[o.y] > cost){
                    distacne[o.y] = cost;
                    pq.offer(new Road(o.y, cost));
                }
            }
        }
        System.out.println(distacne[d]);
    }
}