import java.util.Scanner;

class BJ2531 {

    static int n, d, k, c;
    static int[] arr, visited;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        d = sc.nextInt();
        k = sc.nextInt();
        c = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 스시종류별로 먹었는지 저장
        visited = new int[d + 1];

        System.out.println(slide());
    }

    static int slide() {
        int sushi = 0, coupon;
        // 일단 처음 k개의 슬라이드에 담기
        for (int i = 0; i < k; i++) {
            if (visited[arr[i]] == 0) {
                sushi++;
            }
            visited[arr[i]]++;
        }
        coupon = sushi;
        for (int i = 1; i < n; i++) {
            // 먹은 스시중에 쿠폰번호가 없으면 먹은거 ++
            if (coupon <= sushi) {
                if (visited[c] == 0) {
                    coupon = sushi + 1;
                } else {
                    coupon = sushi;
                }
            }
            // 앞포인터 이동, 앞에꺼 먹은적있으면 --
            visited[arr[i - 1]]--;
            if (visited[arr[i - 1]] == 0) {
                sushi--;
            }
            // 뒤포인터 이동, 뒤에꺼 먹은적없으면 ++
            if (visited[arr[(i + k - 1) % n]] == 0) {
                sushi++;
            }
            visited[arr[(i + k - 1) % n]]++;
        }
        return coupon;
    }
}

// 시간초과로 장렬히 전사한 코드
// import java.io.IOException;
// import java.util.Arrays;
// import java.util.Scanner;

// public class BJ2531 {
// public static void main(String[] args) throws IOException {

// // 입력
// Scanner sc = new Scanner(System.in);
// int N = sc.nextInt();// 벨트에 놓인 접시 수
// int[] arr = new int[N];
// int d = sc.nextInt();// 초밥의 가짓 수
// int k = sc.nextInt();// 연속해서 먹는 수
// int coupon = sc.nextInt();// 쿠폰 번호
// for (int i = 0; i < N; i++) {
// arr[i] = sc.nextInt();
// }
// int[] best = new int[N];
// // 연속한 k개의 배열 뽑아서 tmp에 저장
// for (int i = 0; i < N - k + 1; i++) {
// int[] tmp = new int[k + 1];
// int tmp_idx = 0;
// for (int j = i; j < k + i; j++) {
// tmp[tmp_idx] = arr[j];
// tmp_idx++;
// }
// // 맨 마지막에 쿠폰번호 저장
// tmp[k] = coupon;
// // 중복 제거
// tmp = Arrays.stream(tmp).distinct().toArray();
// // 갯수 best 배열에 저장
// best[i] = tmp.length;
// }
// // 최댓값 출력
// System.out.println(Arrays.stream(best).max().getAsInt());
// }

// }

