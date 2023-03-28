import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ16988_Baaaaaaaaaduk2 {

    static final int[] DR = {-1, +1, -0, +0};
    static final int[] DC = {-0, +0, -1, +1};

    static int[][] grid;
    static boolean[][] visited;

    static class Pair {

        int r;
        int c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Pair pair = (Pair) obj;
            return this.r == pair.r && this.c == pair.c;
        }

        @Override
        public int hashCode() {
            final int PRIME = 31;
            int result = 1;
            result = PRIME * result + this.r;
            result = PRIME * result + this.c;
            return result;
        }
    }

    static class House {

        int size;
        Set<Pair> beingConquered;

        House() {
            this.size = 0;
            beingConquered = new HashSet<>();
        }

        public boolean isConquerable(Set<Pair> pavingStone) {
            for (Pair stone : beingConquered) {
                if (!pavingStone.contains(stone)) {
                    return false;
                }
            }
            return true;
        }

        public int getObtainableReward(Set<Pair> pavingStone) {
            return isConquerable(pavingStone) ? this.size : 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        visited = new boolean[n][m];

        for (int r = 0; r < n; ++r) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < m; ++c) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        List<House> conquerableHouse = new ArrayList<>();
        Set<Pair> beingConquered = new HashSet<>();
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < m; ++c) {
                if (grid[r][c] == 2 && visited[r][c] == false) {
                    House house = getConquerableHouse(r, c);
                    if (house == null) {
                        continue;
                    }
                    for (Pair p : house.beingConquered) {
                        beingConquered.add(p);
                    }
                    conquerableHouse.add(house);
                }
            }
        }

        int answer = 0;

        for (Set<Pair> pavingStone : getPossiblePavingStone(beingConquered)) {
            int obtainableReward = 0;
            for (House house : conquerableHouse) {
                obtainableReward += house.getObtainableReward(pavingStone);
            }
            answer = Math.max(obtainableReward, answer);
        }
        System.out.println(answer);
    }

    public static List<Set<Pair>> getPossiblePavingStone(Set<Pair> beingConqueredStone) {
        List<Set<Pair>> possiblePavingStone = new LinkedList<>();

        if(beingConqueredStone.size() == 1) {
            possiblePavingStone.add(beingConqueredStone);
            return possiblePavingStone;
        }

        Pair[] beingConqueredStoneArray = beingConqueredStone.toArray(new Pair[0]);
        for (int i = 0; i < beingConqueredStoneArray.length - 1; ++i) {
            for (int j = i + 1; j < beingConqueredStoneArray.length; ++j) {
                Set<Pair> pavingStone = new HashSet<>();
                pavingStone.add(beingConqueredStoneArray[i]);
                pavingStone.add(beingConqueredStoneArray[j]);
                possiblePavingStone.add(pavingStone);
            }
        }

        return possiblePavingStone;
    }

    public static House getConquerableHouse(int r, int c) {
        House house = new House();

        // bfs
        Queue<Pair> queue = new LinkedList<>();
        visited[r][c] = true;
        queue.add(new Pair(r, c));
        ++house.size;

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int dir = 0; dir < 4; ++dir) {
                int nextR = cur.r + DR[dir];
                int nextC = cur.c + DC[dir];

                if (nextR < 0 ||
                    nextR >= grid.length ||
                    nextC < 0 ||
                    nextC >= grid[0].length ||
                    visited[nextR][nextC] == true) {
                    continue;
                }

                if (grid[nextR][nextC] == 2) {
                    ++house.size;
                    visited[nextR][nextC] = true;
                    queue.add(new Pair(nextR, nextC));
                } else if (grid[nextR][nextC] == 0) {
                    house.beingConquered.add(new Pair(nextR, nextC));
                }
            }
        }

        return (house.beingConquered.size() > 0 && house.beingConquered.size() <= 2) ? house : null;
    }
}
