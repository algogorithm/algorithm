package week8_0502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_3649 {
	//로봇 프로젝트
	static int x, n;
	static int[] legos;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		while((s=br.readLine()) != null) { 
			//구멍 너비
			x = Integer.parseInt(s)* 10000000;
			
			//레고 조각 수
			n = Integer.parseInt(br.readLine());
			legos = new int[n];
			for(int i=0; i<n; i++) {
				legos[i] = Integer.parseInt(br.readLine());
			}
			
			Arrays.sort(legos);
			
			int left = 0;
			int right = n-1;
			boolean flag = false;
			
			while(left<right) {
				int sum = legos[left] + legos[right];
				
				if(sum == x) { //구멍과 일치하면 이후에 더 있더라도 현재값이 절대값 차이가 가장 큰 값
					flag = true;
					break;
				}else if(sum > x) { //구멍보다 크면 큰쪽을 줄여줌
					right--;
				} else { //구멍보다 작으면 작은쪽을 올려줌
					left++;
				}
			}
			
			if(flag) {
				System.out.println("yes " + legos[left]+" "+ legos[right]);
			} else {
				System.out.println("danger");
			}

		}
	}

}
