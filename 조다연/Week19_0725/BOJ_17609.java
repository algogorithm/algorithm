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
					//문자가 다르면 삭제해서 유사회문이 될 수 있는지
					//둘다 안되는지 확인
					answer++;
					
					//왼쪽 ㄲㅓ 먼저
					if(word.charAt(left) == word.charAt(right-1)
							&& check(left, right-1, word)) break;
					
					//오른쪽
					if(word.charAt(left+1) == word.charAt(right)
							&& check(left+1, right, word)) break;
					
					//한 문자를 삭제하여 회문으로 되면 유사회문이니까
					//위에서 안 걸러지면 유사회문 x
					answer++;
					break;
				}
			}
			sb.append(answer);
			sb.append("\n");
		}
		//회문이면 0, 유사 회문이면 1, 둘 모두 아니면 2
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
