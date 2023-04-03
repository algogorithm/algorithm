package week4_0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2304 {
	//2304. 창고 다각형
	
	static int storage[] = new int[1001];
	static int start = Integer.MAX_VALUE;
	static int end = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//기둥의 개수
		int N = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<N; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			//기둥 폭 1 
			//각 기둥의 왼쪽 면의 위치 L, 높이 H 
			int L = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			
			//L 위치에 높이 H
			storage[L] = H;
			
			//시작, 끝 범위
			start = Math.min(L, start);
			end = Math.max(L, end);
		}
		
		Stack<Integer> stack = new Stack<>();
	
		//왼쪽부터 보기
		int h = storage[start];
		for(int i=start+1; i<=end; i++) {
			//현재 기둥이 전 기둥보다 작다면
			if(storage[i]<h) {
				//스택에 해당 기둥 위치 넣어주기
				stack.push(i);
			} else {
				//현재 기둥이 전 기둥보다 작지 않다면
				//스택에 저장된 위치를 이용하여 전 기둥 높이(h) 넣어주기
				while(!stack.isEmpty()) {
					int idx = stack.pop();
					storage[idx] = h;
				}
				//큰 높이 반영
				h = storage[i];
			}
		}
		//비워
		stack.clear();
		
		//오른쪽부터 보기
		h = storage[end];
		//왼쪽부터 보기
		for(int i=end-1; i>=start; i--) {
			//현재 기둥이 전 기둥보다 작다면
			if(storage[i]<h) {
				//스택에 해당 기둥 위치 넣어주기
				stack.push(i);
			} else {
				//현재 기둥이 전 기둥보다 작지 않다면
				//스택에 저장된 위치를 이용하여 전 기둥 높이(h) 넣어주기
				while(!stack.isEmpty()) {
					int idx = stack.pop();
					storage[idx] = h;
				}
				//큰 높이 반영
				h = storage[i];
			}
		}
		//비워
		stack.clear();
		
		//배열에 저장된 높이 합해주기
		int ans = 0;
		for(int i=start; i<=end; i++) {
			ans += storage[i];
		}
		
		System.out.println(ans);
	}
}
