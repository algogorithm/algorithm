package week18_0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2179 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = br.readLine();
		}
		
		String s = null; 
		String t = null;
		int max = 0;
		for(int i=0; i<N-1; i++) {
			String str1 = arr[i];
			for(int j=i+1; j<N; j++) {
				String str2 = arr[j];
				
				//�� �ܾ� ��
				int size = Math.min(str1.length(), str2.length());
				int cnt = 0;
				for(int k=0; k<size; k++) {
					//���λ� ����
					if(str1.charAt(k) != str2.charAt(k)) break;
					
					cnt++;
				}
				
				//���λ� �ִ��� ���
				if(cnt > max) {
					max = cnt;
					s = arr[i]; t = arr[j];
				}
			}
		}
		
		System.out.println(s);
		System.out.println(t);
	}

}
