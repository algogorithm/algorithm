package week23_0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2116 {
	//주사위 쌓기
	static int N;
	static int answer;
	static int[][] dice;
	//a(0)-f(5) / b(1)-d(3) / c(2)-e(4)
	static int[] set = {5,3,4,1,2,0};

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//주사위 개수
		N = Integer.parseInt(br.readLine());
		dice = new int[N][6];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<6; j++) {
				//0:a, 1:b, 2:c, 3:d, 4:e, 5:f
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		//보여지는 사각면이 제일 큰 수가 되려면
		//맨 처음에 쌓는 주사위는 1을 맨 위로 두어야 함
		for(int i=1; i<=6; i++) {
			find(0, i, 0);
		}
		
		
		System.out.println(answer);
	}

	private static void find(int n, int down, int sum) {
		//주사위를 모두 다 봤으면 그만
		if(n==N) {
			answer = Math.max(answer, sum);
			return;
		}

		int up = findUp(n, down);
		
		//위아래가 5,6/6,5면 젤 큰 수 4
		//한쪽이 6이면 젤 큰거 5
		//그 외 6
		int max = 0; 
		if(up+down==11) {
			max = 4;
		}else if(up==6||down==6) {
			max = 5;
		} else max = 6;
		
		//현재 주사위의 위는 다음 주사위의 아래 숫자가 됨
		find(n+1, up, sum+max);
	}

	private static int findUp(int n, int down) {
		//다음 주사위의 위는 다음 주사위의 아래를 통해 찾아줘야 함  (짝꿍 숫자)
		//다음 주사위의 아래는 1이야.. 
		//dice[1][1] = 1이지?
		//1:3이니까 dice[1][3]=4지
		//4를 넘겨줘야 돼
		int idx = 0;
		for(int i=0; i<dice[n].length; i++) {
			if(dice[n][i]==down) {
				idx = i;
				break;
			}
		}
		return dice[n][set[idx]];
	}

}
