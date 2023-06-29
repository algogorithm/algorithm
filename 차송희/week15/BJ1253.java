
/*
 * 백준 1253 좋다
 * https://www.acmicpc.net/problem/1253
 * 
 * 투포인터 알고리즘
 */
import java.util.*;
import java.io.*;

public class BJ1253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int nums[] = new int[N];
		int answer = 0;

		// 파싱하는거 까먹어서 헤매기 ~!
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 배열 정렬
		Arrays.sort(nums);

		for (int i = 0; i < N; i++) {
			int target = nums[i];
			int left = 0, right = N - 1;

			// 투 포인터 알고리즘
			int sum = 0;
			while (left < right) {
				sum = nums[left] + nums[right];

				if (sum == target) {
					if (left != i && right != i) {
						answer++;
						break;
					} else if (left == i) {
						left++;
					} else {
						right--;
					}
				} else if (sum < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		System.out.println(answer);
	}
}