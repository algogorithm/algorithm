import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16235_나무_재테크 {
	static class Tree implements Comparable<Tree>{
		int age;
		boolean isDead;
		
		Tree(int age){
			this.age = age;
		}

		@Override
		public int compareTo(Tree t) {
			return this.age - t.age;
		}
	}
	static class Soil{
		List<Tree> t = new ArrayList<>();
		int r;
		int c;
		int nutrition;
		
		Soil(int r, int c, Tree t){
			this.r = r;
			this.c = c;
			this.t.add(t);
		}
	}
	static ArrayList<Soil>[][] MAP;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // NxN 사이즈 맵
		int M = Integer.parseInt(st.nextToken()); // 심은 나무의 정보(위치x, 위치y, 나이)
		int K = Integer.parseInt(st.nextToken()); // K년 지난 후
		List<Soil> soils = new ArrayList<>();
		MAP = new Soil[N][N];
		int[][] nutrition = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				nutrition[i][j] = Integer.parseInt(st.nextToken());
				MAP[i][j].nutrition = 5;
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			MAP[x][y] = new Soil(x, y, new Tree(age));
		}
		
		while(--K > 0) {
			for(int i=0; i<soils.size(); i++) {
				Soil s = soils.get(i);
				for(int j=0; j<s)
			}
			
			
			
			// 영양소 추가
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					MAP[i][j] += nutrition[i][j];
				}
			}
		}
		
		
	}
	
	private void addNutrition() {
		
	}

}
