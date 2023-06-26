import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BOJ2668_숫자고르기 {

    static int[] link;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        LinkedList<Integer> answer = new LinkedList<>();
        link = new int[N+1];
        visited = new boolean[N+1];

        for(int i = 1; i <= N; ++i) {
            link[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i <= N; ++i) {
            visited[i] = true;
            if(hasCycle(i, link[i])) answer.add(i);
            visited[i] = false;
        }

        System.out.println(answer.size());
        for(int ans : answer) {
            System.out.println(ans);
        }
    }

    public static boolean hasCycle(int origin, int next) {
        if(origin == next) return true;

        if(visited[next]) return false;
        visited[next] = true;
        boolean hasCycle = hasCycle(origin, link[next]);
        visited[next] = false;
        return hasCycle;
    }
}
