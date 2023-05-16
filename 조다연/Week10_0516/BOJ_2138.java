package week10_0516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2138 {
	//전구와 스위치
	static int N;
	static int answer = 987654321;
	static boolean[] arr1, arr2, after;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //전구 수
		
		arr1 = new boolean[N];
		arr2 = new boolean[N];
		after = new boolean[N];
		
		//현재 상태 전구
		String current = br.readLine();		
		//만들고자 하는 상태 전구
		String wish = br.readLine();
		
		for(int i=0; i<N; i++) {
			arr1[i] = current.charAt(i) != '0';
			arr2[i] = current.charAt(i) != '0';
			after[i] = wish.charAt(i) != '0';
		}

		//첫 번째 전구 이용 x
		bulb(1, 0, arr1);
		//첫 번째 전구 이용 o -> 첫 번째 전구 상태 바꿔주기
		bulb(1, 1, swicth(0, arr2));
		
		System.out.println(answer == 987654321 ? -1 : answer);
	}

	private static void bulb(int idx, int cnt, boolean[] arr) {
		//마지막까지 봤으면
		if(idx == N) {
			//상태 똑같으면 완성
			if(arr[idx-1] == after[idx-1]) {
				answer = Math.min(answer, cnt);
			}
			return;
		}
		
		if(arr[idx-1] != after[idx-1]) {
			//전 스위치의 상태가 다르면 현 스위치 상태 바꿔줘서 맞춰주기
			bulb(idx+1, cnt+1, swicth(idx, arr));
		} else {
			//상태 같으면 다음으로 넘어가
			bulb(idx+1, cnt, arr);
		}
		
	}

	// 전구 상태 0,1 바꿔주기
	private static boolean[] swicth(int idx, boolean[] arr) {
		//i-1, i, i+1
		for(int i=idx-1; i<=idx+1; i++) {
			if(i>=0 && i<N) {
				arr[i] = !arr[i];
			}
		}
		return arr;
	}

}
