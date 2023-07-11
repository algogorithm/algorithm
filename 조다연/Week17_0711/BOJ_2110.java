package week17_0711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
	//������ ��ġ

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //��
		int C = Integer.parseInt(st.nextToken()); //������
		
		int[] house = new int[N];
		for(int i=0; i<N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(house);
		
		int start = 1; //�ּҰŸ�
		int end = house[N-1] - house[0];//�ִ�Ÿ�

		int answer = 0;
		
		while(start <= end) {
			int mid = (start+end)/2;
			int install = house[0];//�������� ��ġ�� ��
			int cnt = 1; //������ ����(�ǿ����� ��ġ�ϰ� ����)
			
			for(int i=1; i<N; i++) {
				if(house[i]-install>=mid) {
					//�Ÿ����� ���غ��� ũ�ų� ���ƾ� ��ġ ����
					cnt++;
					install = house[i]; //��ġ�ߴٸ� �� ��� �ٲ��ֱ�
				}
			}
			
			if(cnt>=C) {
				//���� ��ġ�� �����⺸�� ���� ��ġ�ߴٸ�
				//���������� �̵��� ������ ����
				answer = mid;
				start = mid+1;
			} else {
				//�����⸦ c���� ���� ��ġ�ߴٸ�
				//�������� �̵��� ���� �ٿ�
				end = mid-1;
			}
		}
		
		System.out.println(answer);
	}

}
