import java.io.*;
import java.util.*;

public class BOJ16235_나무_재태크 {

    static final int[] DR = {-1, -1, -1,
                            -0,      +0,
                            +1, +1, +1};
    static final int[] DC = {-1, +0, +1,
                            -1,      +1,
                            -1, +0, +1};

    static class Tree implements Comparable<Tree> {
        int r;
        int c;
        int age;

        Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] fertilizerSupplies = new int[n + 1][n + 1];
        int[][] land = new int[n + 1][n + 1];

        for(int r = 1; r <= n; ++r) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int c = 1; c <= n; ++c) {
                fertilizerSupplies[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for(int r = 1; r <= n; ++r) {
            Arrays.fill(land[r], 5);
        }

        List<Tree> tree = new LinkedList<>();
        for(int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            tree.add(new Tree(r, c, age));
        }

        Deque<Tree> plantedTree = new LinkedList<>();
        Collections.sort(tree);
        for(Tree t : tree) {
            plantedTree.addLast(t);
        }

        Queue<Tree> breedingTree = new LinkedList<>();
        Queue<Tree> deadTree = new LinkedList<>();

        for (int year = 0; year < k; ++year) {
            // 봄
            int numOfTrees = plantedTree.size();
            for(int i = 0; i < numOfTrees; ++i) {
                Tree curTree = plantedTree.pollFirst();

                // 양분을 먹지 못하고 죽음
                if (curTree.age > land[curTree.r][curTree.c]) {
                    deadTree.add(curTree);
                    continue;
                }

                // 양분 먹음
                land[curTree.r][curTree.c] -= curTree.age;
                // 나이 증가
                ++curTree.age;

                // 번식 예정
                if(curTree.age % 5 == 0) {
                    breedingTree.add(curTree);
                }

                plantedTree.addLast(curTree);
            }

            // 여름
            while(!deadTree.isEmpty()) {
                Tree dead = deadTree.poll();
                // 양분 추가
                land[dead.r][dead.c] += dead.age / 2;
            }

            // 가을
            while(!breedingTree.isEmpty()) {
                Tree breeding = breedingTree.poll();

                for(int dir = 0; dir < DR.length; ++dir) {
                    int nextR = breeding.r + DR[dir];
                    int nextC = breeding.c + DC[dir];

                    if(nextR < 1
                        || nextR > n
                        || nextC < 1
                        || nextC > n)
                        continue;

                    // 아가 나무 심기
                    plantedTree.addFirst(new Tree(nextR, nextC, 1));
                }
            }

            // 겨울
            for(int r = 1; r <= n; ++r) {
                for(int c = 1; c <= n; ++c) {
                    land[r][c] += fertilizerSupplies[r][c];
                }
            }
        }

        System.out.println(plantedTree.size());
    }
}