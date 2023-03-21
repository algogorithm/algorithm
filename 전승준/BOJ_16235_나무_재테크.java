import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
			return t.age - this.age;
		}

		@Override
		public String toString() {
			return "Tree [r=" + r + ", c=" + c + ", age=" + age + "]";
		}
	}
	static int[] R = {-1,-1,-1,0,0,1,1,1};
	static int[] C = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // NxN 사이즈 맵
		int M = Integer.parseInt(st.nextToken()); // 심은 나무의 정보(위치x, 위치y, 나이)
		int K = Integer.parseInt(st.nextToken()); // K년 지난 후
		int[][] soil = new int[N+1][N+1];
		int[][] nutrition = new int[N+1][N+1];
		ArrayList<Tree> liveTrees = new ArrayList<>();
		Queue<Tree> deadTrees = new LinkedList<>();
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				nutrition[i][j] = Integer.parseInt(st.nextToken());
				soil[i][j] = 5;
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			liveTrees.add(new Tree(x, y, age));
		}

		while(K-- > 0) {
			Collections.sort(liveTrees);
			// 봄 (양분 먹고 나이 1 증가)
			for(int i=liveTrees.size()-1; i>=0; i--) {
				Tree t = liveTrees.get(i);
				
				if(soil[t.r][t.c] >= t.age) {
					soil[t.r][t.c] -= t.age;
					liveTrees.get(i).age++;
				} else {
					liveTrees.remove(i);
					deadTrees.add(new Tree(t.r, t.c, t.age));
				}
			}
			
			
			// 여름 (죽은 나무가 양분으로 변함)
			int qSize = deadTrees.size();
			for(int i=0; i<qSize; i++) {
				Tree t = deadTrees.poll();
				
				soil[t.r][t.c] += t.age/2;
			}
			
			// 가을 (나무 번식)
			for(int i=liveTrees.size()-1; i>=0; i--) {
				Tree t = liveTrees.get(i);
				
				if(t.age % 5 == 0) {
					for(int j=0; j<8; j++) {
						int nr = R[j] + t.r;
						int nc = C[j] + t.c;
						
						if(nr>=1 && nr<=N && nc>=1 && nc<=N) {
							liveTrees.add(new Tree(nr, nc, 1));
						}
					}
				}
			}
			
			// 겨울(영양소 추가)
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					soil[i][j] += nutrition[i][j];
				}
			}
		}
		
		System.out.println(liveTrees.size());
	}
}