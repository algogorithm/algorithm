package week16_0704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531 {
	//ȸ���ʹ�
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
	
		int[] sushi = new int[N+k];
		for(int i=0; i<N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int[] num = new int[d+1]; //����üũ
		num[c]++; //
		int cnt = 1; //c �ʹ� ������ �Ծ�
		
		//�����ؼ� �Դ� ���� ����ŭ�� 
		for(int i=0; i<k; i++) {
			sushi[N+i] = sushi[i];
			num[sushi[i]]++;
			
			if(num[sushi[i]]==1) cnt++; // 7 9 7 30�̸� cnt=3
		}
		
		//���� k���� k�� 
		int answer = cnt;
		for(int i=k; i<N+k-1; i++) {
			num[sushi[i-k]]--; //�� ���� ��
			if(num[sushi[i-k]]==0) cnt--; //���������� cnt--
			
			num[sushi[i]]++; //�� ���� �־�
			if(num[sushi[i]]==1) cnt++; //���ο� �ʹ��̸� cnt++

			answer = Math.max(answer, cnt);
		}
		
		System.out.println(answer);
	}
}
