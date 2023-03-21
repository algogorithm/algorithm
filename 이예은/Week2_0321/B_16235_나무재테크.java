package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16235 {
	static int R[] = {0,0,1,-1,-1,-1,1,1};
	static int C[] = {1,-1,0,0,1,-1,1,-1};
	
	static class Tree implements Comparable<Tree>{
		int r, c, a;
		Tree(int r, int c, int a) {
			this.r = r;
			this.c = c;
			this.a = a;
		}
		@Override
		public int compareTo(Tree o) {
			return this.a-o.a;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int G[][] = new int[N][N];
		int A[][] = new int[N][N];
		Deque<Tree> trees = new ArrayDeque<Tree>();
		PriorityQueue<Tree> list = new PriorityQueue<Tree>();
		Queue<Tree> fall = new LinkedList<Tree>();
		Queue<Tree> die = new LinkedList<Tree>();
		
		for(int i=0; i<N; i++)
			Arrays.fill(G[i], 5);
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Tree(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
		}
		
		// 처음 나무들만 정렬
		while(!list.isEmpty())
			trees.add(list.poll());
		
		while(K-- > 0) {
			// 봄 : 모든 나무들 양분 먹기
			int s = trees.size();
			for(int i=0; i<s; i++) {
				Tree t = trees.poll();

				if(G[t.r][t.c] >= t.a) { // 양분 먹을 수 있으면
					G[t.r][t.c] -= t.a;
					trees.add(new Tree(t.r, t.c, t.a+1)); // 나이 한 살 더 먹고 그대로 리스트에 뒤로 넣기
					if((t.a+1) % 5 == 0)	fall.add(new Tree(t.r, t.c, t.a+1)); // 나이가 5의 배수이면 가을에 번식
				}
				else	die.add(t); // 죽은 나무
			}
			// 여름 : 죽은 나무들 양분으로 변하기
			while(!die.isEmpty()) {
				Tree t = die.poll();
				G[t.r][t.c] += t.a/2;
			}
			// 가을 : 나무 번식 (다들 나이가 1이므로 리스트에 앞으로 넣으면 됨)
			while(!fall.isEmpty()) {
				Tree t = fall.poll();
				for (int j = 0; j < 8; j++) {
					int dr = t.r + R[j];
					int dc = t.c + C[j];
					if(dr>=0 && dr<N && dc>=0 && dc<N)
						trees.addFirst(new Tree(dr, dc, 1));
				}
			}
			// 겨울 : 양분 추가
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++)
					G[i][j] += A[i][j];
			}
		}
		
		System.out.print(trees.size());
	}
}