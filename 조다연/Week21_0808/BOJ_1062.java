package week21_0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1062 {
	//����ħ
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
			//���ĺ� 26���̹Ƿ� 26���� ��� ���� �� ����
			System.out.println(N);
			return;
		} else {
			//���λ� anta�� ���̻� tica ����
			for(int i=0; i<N; i++) {
				String s = br.readLine();
				arr[i] = s.substring(4,s.length()-4);
			}
			
			//antic 5���� �ܾ�� �ݵ�� ����
			K-=5;
			
			//antic �湮 üũ
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
					//K�� ����� ��, �� �ܾ�� �ش� ���ڿ� ���� �� �ִ��� �Ǵ�
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
		
		//���� K���� ����� ���� ��� -> ��Ž(���ĺ� 26��)
		for(int i=start; i<26; i++) {
			if(!v[i]) {
				v[i] = true;
				dfs(i, cnt+1);
				v[i] = false;
			}
		}
		
	}

}
