import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16235_나무_재테크 {
	static class Tree implements Comparable<Tree>{
		int r;
		int c;
		int age;
		
		Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree t) {
			return this.age - t.age;
		}

		@Override
		public String toString() {
			return "Tree [r=" + r + ", c=" + c + ", age=" + age + "]";
		}

	}
	static int[] DR = {-1,-1,-1,0,0,1,1,1};
	static int[] DC = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // NxN 사이즈 맵
		int M = Integer.parseInt(st.nextToken()); // 심은 나무의 정보(위치x, 위치y, 나이)
		int K = Integer.parseInt(st.nextToken()); // K년 지난 후
		int[][] soil = new int[N+1][N+1];
		int[][] nutrition = new int[N+1][N+1];
		
		ArrayList<Tree> inputTree = new ArrayList<>();
		Deque<Tree> plantedTree = new ArrayDeque<>();
		Queue<Tree> bornTree = new LinkedList<>();
		Queue<Tree> deadTree = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				nutrition[i][j] = Integer.parseInt(st.nextToken());
				soil[i][j] = 5;
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			inputTree.add(new Tree(x, y, age));
		}
		
		Collections.sort(inputTree);
		for(Tree t : inputTree) {
			plantedTree.addLast(t);
		}

		while(K-- > 0) {
			// 봄 (양분 먹고 나이 1 증가)
			int qSize = plantedTree.size();
			for(int i=0; i<qSize; i++) {
				Tree curTree = plantedTree.pollFirst();
				
				if(soil[curTree.r][curTree.c] >= curTree.age) {
					soil[curTree.r][curTree.c] -= curTree.age;
					curTree.age++;
					if(curTree.age % 5 == 0) bornTree.add(curTree);
					plantedTree.addLast(curTree);
				} else {
					deadTree.add(new Tree(curTree.r, curTree.c, curTree.age));
				}
			}
			
			// 여름 (죽은 나무가 양분으로 변함)
			while(!deadTree.isEmpty()) {
				Tree dead = deadTree.poll();
				
				soil[dead.r][dead.c] += dead.age/2;
			}
			
			// 가을 (나무 번식)
			while(!bornTree.isEmpty()) {
				Tree born = bornTree.poll();
				
				for(int i=0; i<8; i++) {
					int nr = born.r + DR[i];
					int nc = born.c + DC[i];
					
					if(nr>=1 && nr<=N && nc>=1 && nc<=N) {
						plantedTree.addFirst(new Tree(nr, nc, 1));
					}
				}
			}
			
			// 겨울(영양소 추가)
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					soil[i][j] += nutrition[i][j];
				}
			}
		}
	
		System.out.println(plantedTree.size());
	}
}