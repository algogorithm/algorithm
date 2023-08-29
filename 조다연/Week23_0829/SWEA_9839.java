package week23_0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_9839 {
	//�ְ��� ��

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			if(N==1) {
				int a = Integer.parseInt(st.nextToken());
				sb.append("#"+tc+" "+a+"\n");
			} else {
				int[] arr = new int[N];
				for(int i=0; i<N; i++) {
					arr[i] = Integer.parseInt(st.nextToken());
				}
				//���� �������� �ʴ� ���� -1
				int max = -1;
				
				//�� ���� ������ ���ϰ� �����ϴ��� Ȯ���ϱ�
				for(int i=0; i<N-1; i++) {
					for(int j=i+1; j<N; j++) {
						int num = arr[i]*arr[j];
						if(check(num)) {
							max = Math.max(max, num);
						}
					}
				}
				
				sb.append("#"+tc+" "+max+"\n");
			}
		}
		
		//������ println���� �ؼ� ��� �ϳ� Ʋ����;;
		System.out.print(sb);
	}

	private static boolean check(int n) {
		String s = n+"";
		
		for(int i=0; i<s.length()-1; i++) {
			if(Integer.parseInt(s.substring(i, i+1))+1 
					!= Integer.parseInt(s.substring(i+1, i+2))) {
				return false;
			}
		}
		
		return true;
	}
}
