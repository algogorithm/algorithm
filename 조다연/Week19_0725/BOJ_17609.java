package week19_0725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			String word = br.readLine();
			
			int left = 0; 
			int right = word.length()-1;
			int answer = 0;
			
			while(left < right) {
				if(word.charAt(left) == word.charAt(right)) {
					left++;
					right--;
				} else {
					//���ڰ� �ٸ��� �����ؼ� ����ȸ���� �� �� �ִ���
					//�Ѵ� �ȵǴ��� Ȯ��
					answer++;
					
					//���� ���� ����
					if(word.charAt(left) == word.charAt(right-1)
							&& check(left, right-1, word)) break;
					
					//������
					if(word.charAt(left+1) == word.charAt(right)
							&& check(left+1, right, word)) break;
					
					//�� ���ڸ� �����Ͽ� ȸ������ �Ǹ� ����ȸ���̴ϱ�
					//������ �� �ɷ����� ����ȸ�� x
					answer++;
					break;
				}
			}
			sb.append(answer);
			sb.append("\n");
		}
		//ȸ���̸� 0, ���� ȸ���̸� 1, �� ��� �ƴϸ� 2
		System.out.println(sb);
	}

	private static boolean check(int left, int right, String word) {
		while(left < right) {
			if(word.charAt(left) == word.charAt(right)) {
				left++;
				right--;
			} else return false;
		}

		return true;
	}

}
