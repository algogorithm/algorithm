package day1_0313;

import java.util.Scanner;

public class BOJ_11279_1 {
	//쉽게 1부터 시작하기 위해서
	static int heap[] = new int[100001];
	//마지막 순서 값
	static int top=0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//연산의 개수
		int N = sc.nextInt();
		
		//연산의 개수만큼 정수 x 받기
		for(int i=0; i<N; i++) {
			int x = sc.nextInt();
			
			//주어진 x가 0이라면 
			//배열에서 가장 큰 값 출력하고 해당 값 제거하기
			if(x == 0) {
				//배열이 비어 있다면 0 출력
				if(top == 0) {
					System.out.println("0");
				}
				//비어 있지 않다면 가장 큰 값 출력 및 제거
				else {
					System.out.println(pop());
				}
			}
			//0이 아니라면 값 넣어주기
			else {
				push(x);
			}
			
		}
	}
	
	private static void push(int x) {
		//0은 비워두고 1부터 시작하기
		//순서 제일 뒤에 해당 값 넣어주기
		heap[++top] = x;
		int idx = top;
		//첫 번째로 들어온 숫자가 아니며 
		//부모 노드 수보다 마지막으로 들어온 노드 숫자가 크다면
		//힙 정렬 해주기
		while(idx>1 && heap[idx/2] < heap[idx]) {
			//앞선 부모노드 작은 수와 나중에 들어온 큰 수의 자리를 바꿔주기
			swap(idx, idx/2);
			idx/=2;
		}
		
	}

	private static void swap(int i, int j) {
		int tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
	}

	private static int pop() {
		//제일 큰 값은 제일 앞에 있는 수
		int result = heap[1];
		
		//제일 뒤에 있는 값을 첫 번째로 옮긴 후 힙 정렬 다시 해주기
		heap[1] = heap[top];
		//마지막 순서 값 비워준 후 순서 값 변경해주기
		heap[top--]=0;
		
		for(int idx=1; idx*2<=top;) {
			//이미 값이 크다면 변경해줄 필요 x
			if(heap[idx]>heap[idx*2] && heap[idx]>heap[idx*2+1]) break;
			
			//왼쪽과 오른쪽 노드 중 더 큰 값을 부모 노드와 바꾸기
			//왼쪽 값이 더 크다면
			else if(heap[idx*2]>heap[idx*2+1]) {
				swap(idx, idx*2);
				idx=idx*2;
			}
			//오른쪽 값이 더 크다면
			else {
				swap(idx, idx*2+1);
				idx=idx*2+1;
			}
		}
		
		//최대값 반환
		return result;
	}
}
