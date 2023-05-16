package d202305;

import java.util.*;
import java.io.*;

public class 무튜브 {

    static int N,Q;
    static ArrayList<ArrayList<Node>> list;
    static class Node{
        int next, usado;

        public Node(int next, int usado) {
            super();
            this.next = next;
            this.usado = usado;
        }

        @Override
        public String toString() {
            return "Node [next=" + next + ", usado=" + usado + "]";
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        list = new ArrayList<ArrayList<Node>>();
        for(int i=0; i<=N; i++)
            list.add(new ArrayList<>());

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            list.get(p).add(new Node(q, r));
            list.get(q).add(new Node(p, r));
        }

        StringBuilder sb = new StringBuilder();
        for(int tc=1; tc<=Q; tc++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); // K 이상인 동영상 추천 
            int v = Integer.parseInt(st.nextToken()); // v를 보고있는 소들에게 몇 개의 동영상 추천?

            // v->?로 가는데 K이상인 개수 구하기 
            sb.append(findMin(k, v)).append('\n');
        }

        System.out.println(sb.toString());


    }

    private static int findMin(int k, int v) {
        // a -> 모든 경로의 최솟값 구하기 
        // 최솟값을 가지고 BFS를 수행한다. 
        boolean [] check = new boolean[N+1];
        check[v] = true;

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(v, Integer.MAX_VALUE));

        int cnt = 0;
        while(!queue.isEmpty()) {
            Node out = queue.poll();
            int nowNode = out.next;

            for(int i=0; i<list.get(nowNode).size(); i++) {
                Node next = list.get(nowNode).get(i);
                int nextNode = next.next;
                int nextUsado = next.usado;

                if(!check[nextNode] && nextUsado >= k) { // 들른적 없는 곳이면 
                    queue.offer(new Node(nextNode, nextUsado));
                    check[nextNode] = true;
                    cnt+=1;
                }
            }
        }

        return cnt;
    }
}
