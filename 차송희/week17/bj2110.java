import java.util.*;

public class BJ2110 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int C = scanner.nextInt();

        int[] houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = scanner.nextInt();
        }

        Arrays.sort(houses);

        // 최소 거리와 최대 거리의 범위를 초기화
        int minDistance = 1;
        int maxDistance = houses[N - 1] - houses[0];

        int answer = 0; // 최대 인접 거리

        while (minDistance <= maxDistance) {
            int midDistance = (minDistance + maxDistance) / 2;
            int count = 1;
            int start = houses[0];

            for (int i = 1; i < N; i++) {

                if (houses[i] - start >= midDistance) {
                    count++;
                    start = houses[i];
                }
            }

            if (count >= C) {
                answer = midDistance;
                minDistance = midDistance + 1;
            } else {
                maxDistance = midDistance - 1;
            }
        }

        System.out.println(answer);
    }
}

