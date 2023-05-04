package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold5_3649_로봇프로젝트 {

	static int x;
	static int[] lego;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = null;
		while((str = br.readLine()) != null && !str.isEmpty()) {
			x = Integer.parseInt(str) * 10000000;
			int n = Integer.parseInt(br.readLine());
			lego = new int[n];

			for (int i = 0; i < n; i++) {
				lego[i] = Integer.parseInt(br.readLine());
			}

			Arrays.sort(lego);

			searchArr(0, n-1);

			str = null;
		}

		System.out.println(sb);
	}

	private static void searchArr(int start, int end) {
		if(start >= end) {
			sb.append("danger").append("\n");
			return;
		}

		if(lego[start] + lego[end] == x) {
			sb.append("yes ").append(lego[start]+" ").append(lego[end]).append("\n");
			return;
		} else if(lego[start] + lego[end] > x) {
			searchArr(start, end-1);
		} else {
			searchArr(start+1, end);
		}
	}
}