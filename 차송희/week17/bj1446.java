import java.util.*;

public class BJ1446 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 지름길의 개수
        int D = scanner.nextInt(); // 고속도로의 길이

        // 각 위치까지의 최소 이동 거리를 저장할 배열
        int[] min = new int[D + 1];
        Arrays.fill(min, Integer.MAX_VALUE);
        min[0] = 0;

        // 지름길 정보를 저장할 리스트
        List<int[]> shortcuts = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int start = scanner.nextInt(); // 지름길의 시작 위치
            int end = scanner.nextInt(); // 지름길의 도착 위치
            int distance = scanner.nextInt(); // 지름길의 이동 거리

            // 지름길 정보를 리스트에 추가
            shortcuts.add(new int[] { start, end, distance });
        }

        // 도로를 순회하면서 최소 이동 거리를 갱신
        for (int i = 0; i < D; i++) {
            // 현재 위치에서 1만큼 이동하는 경우
            if (min[i] + 1 < min[i + 1]) {
                min[i + 1] = min[i] + 1;
            }

            // 현재 위치에서 지름길을 이용하여 이동하는 경우
            for (int[] shortcut : shortcuts) {
                int start = shortcut[0];
                int end = shortcut[1];
                int distance = shortcut[2];

                if (i == start && end <= D && min[i] + distance < min[end]) {
                    min[end] = min[i] + distance;
                }
            }
        }

        System.out.println(min[D]); // 도착 지점까지의 최소 이동 거리 출력

        scanner.close();
    }
}
