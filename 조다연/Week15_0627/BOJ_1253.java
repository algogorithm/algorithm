package week15_0627;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253 {
	//����

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0; 
		
		Arrays.sort(arr);
		
		//N�� 2 ���ϸ� ���� ���� 0����
		if(N>2) {
			for(int i=0; i<N; i++) {
				int left = 0; int right = N-1;
				int num = arr[i];
				
				while(left < right) {
					//�ڱ� �ڽ��� �ϸ� �� �Ǳ� ������ �������� �Ѿ
					if(left == i) {
						left++; continue;
					}
					if(right == i) {
						right--; continue;
					}
					
					
					if(arr[left]+arr[right] == num) {
						//���� ���� ���� ���ٸ� ����, ã���� �׸�
						answer++; break;
					}
					//���� ���� ������ �۴ٸ� left++
					else if(arr[left]+arr[right] < num) left++;
					//���� ���� ������ ũ�ٸ� right--
					else right--;
				}
			}
		}
		
		System.out.println(answer);
		
	}

}
