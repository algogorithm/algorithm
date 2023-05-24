package week11_0523;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {
	//암호 만들기
	static int L, C;
	static String[] key, alpa;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		key = new String[L];
		alpa = new String[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			alpa[i] = st.nextToken();
		}
		
		//미리 알파벳 정렬
		Arrays.sort(alpa);
		combination(0, 0);
	}

	private static void combination(int cnt, int start) {
		if(cnt == L) { //L개 선택 했으면
			if(check(key)) { //조건에 부합하는지 체크
				for(String s : key) {
					System.out.print(s);
				}
				System.out.println();
			}
			return;
		}
		
		for(int i=start; i<C; i++) {
			key[cnt] = alpa[i];
			combination(cnt+1, i+1);
		}
		
	}

	private static boolean check(String[] arr) {
		int con = 0; //자음 
		int vow = 0; //모음
		
		for(int i=0; i<arr.length; i++) {
			//모음이면
			if(arr[i].equals("a") || arr[i].equals("e") || arr[i].equals("i")
					|| arr[i].equals("o") || arr[i].equals("u")) {
				vow++;
			} else { //자음이면
				con++;
			}
		}
		
		//자음 2개 이상 모음 1개 이상 조건 맞으면 true
		if(con>=2 && vow>=1) return true;
		else return false;
	}

}
