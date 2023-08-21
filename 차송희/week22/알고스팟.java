import java.util.*;

class Node implements Comparable<Node> {
    int x, y, cost;

    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class bj1261 {

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        char[][] map = new char[m][n];

        for (int i = 0; i < m; i++) {
            String line = scanner.nextLine();
            map[i] = line.toCharArray();
        }

        int result = solve(map, m, n);
        System.out.println(result);
    }

    public static int solve(char[][] map, int m, int n) {
        int[][] dist = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], 10000);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int x = node.x;
            int y = node.y;
            int cost = node.cost;

            if (x == m - 1 && y == n - 1) {
                return cost;
            }

            if (cost > dist[x][y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int newCost = cost + (map[nx][ny] - '0');
                    if (newCost < dist[nx][ny]) {
                        dist[nx][ny] = newCost;
                        pq.add(new Node(nx, ny, newCost));
                    }
                }
            }
        }

        return -1;
    }
}
