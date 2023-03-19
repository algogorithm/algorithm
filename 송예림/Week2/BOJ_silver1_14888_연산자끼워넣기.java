package BAEKJOON;

import java.io.*;
import java.util.*;

public class silver1_14888_연산자끼워넣기 {
	static int[] number;
	static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] cal = new int[4];
		number = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < number.length; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cal.length; i++) {
			cal[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1, number[0], cal);
		System.out.println(max + "\n" + min);
	}

	private static void dfs(int num, int sum, int[] cal) {
		if(num == number.length) {
			if(max < sum) {
				max = sum;
			}
			if(min > sum) {
				min = sum;
			}
			return;
		}
		
		for (int d = 0; d < cal.length; d++) {
			if(cal[d] > 0) {
				cal[d]--;
				switch(d) {
				case 0:
					dfs(num+1, sum + number[num], cal);
					break;
				case 1:
					dfs(num+1, sum - number[num], cal);
					break;
				case 2:
					dfs(num+1, sum * number[num], cal);
					break;
				case 3:
					dfs(num+1, sum / number[num], cal);
					break;
				}
				cal[d]++;
			}
		}
	}
}