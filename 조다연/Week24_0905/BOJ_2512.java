package week24_0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2512 {
	//����

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] city = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			city[i] = Integer.parseInt(st.nextToken());
		}
		
		//�� ����
		int budge = Integer.parseInt(br.readLine());
		
		int sum = 0, answer = 0;
		
		//���� ������ �ֱ� ���� ����
		Arrays.sort(city);
		
		int cnt = N;
		boolean flag = false;
		for(int i=0; i<N; i++) {
			//���� ���� ���� ������ŭ ���� ���� ������ �� ������ 
			//�ش� ���� ������ ���� �� �ֻ�
			int num = sum + city[i]*cnt;
			
			if(num > budge) {
				//������ �Ѱ� �Ǹ� �ش� ���� �ϴ� ���� ū ���� �ǰ�
				//���Ѿ��� ���ؾ� ��
				answer = city[i];
				flag = true;
				break;
			}
			
			sum += city[i];
			cnt--;
		}
		
		while(flag) {
			//���� ū ���� ���� �ٿ������鼭 ���Ѿ� ã��
			if(sum + answer*cnt <= budge) break;
			answer--;
		}
		
		//���� �� �Ѱ� �� �� ���� �� ������ ���� ū ����
		if(!flag) answer = city[N-1];
		System.out.println(answer);
	}

}
