package week21_0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1062 {
	//가르침
	static int N, K;
	static int max = 0;
	static boolean v[] = new boolean[26];
	static String[] arr;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new String[N];
		
		if(K<5) {
			System.out.println("0");
			return;
		} else if(K==26) { 
			//알파벳 26개이므로 26개면 모두 읽을 수 있음
			System.out.println(N);
			return;
		} else {
			//접두사 anta와 접미사 tica 제거
			for(int i=0; i<N; i++) {
				String s = br.readLine();
				arr[i] = s.substring(4,s.length()-4);
			}
			
			//antic 5개의 단어는 반드시 포함
			K-=5;
			
			//antic 방문 체크
			v['a'-'a'] = true;
			v['n'-'a'] = true;
			v['t'-'a'] = true;
			v['i'-'a'] = true;
			v['c'-'a'] = true;
			
			dfs(0,0);
			System.out.println(max);
		}
	}

	private static void dfs(int start, int cnt) {
		if(cnt==K) {
			int num = 0;
			for(int i=0; i<arr.length; i++) {
				boolean flag = true;
				for(int j=0; j<arr[i].length(); j++) {
					//K개 골랐을 때, 고른 단어로 해당 문자열 읽을 수 있는지 판단
					if(!v[arr[i].charAt(j)-'a']) {
						flag = false;
						break;
					}
				}
				
				if(flag) num++;
			}
			
			max = Math.max(max, num);
			return;
		}
		
		//아직 K개를 배우지 않은 경우 -> 완탐(알파벳 26개)
		for(int i=start; i<26; i++) {
			if(!v[i]) {
				v[i] = true;
				dfs(i, cnt+1);
				v[i] = false;
			}
		}
		
	}

}
