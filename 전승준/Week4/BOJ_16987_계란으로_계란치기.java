package Week4;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16987_계란으로_계란치기 {
	static class Egg {
		int durability;
		int weight;
		
		Egg(int durability, int weight){
			this.durability = durability;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Egg [durability=" + durability + ", weight=" + weight + "]";
		}
		
	}
	static int Ans = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		Egg[] eggs = new Egg[T];
		
		for(int i=0; i<T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), 
							  Integer.parseInt(st.nextToken()));
		}
		
		pickEgg(0, eggs);
		System.out.println(Ans);
	}



	private static void pickEgg(int idx, Egg[] eggs) {
		if(idx >= eggs.length) {
			int cnt = 0;
			
			for(int i=0; i<eggs.length; i++) {
				if(eggs[i].durability <= 0) cnt++;
			}
			
			Ans = Math.max(Ans, cnt);
			
			return;
		}
		
		boolean flag = false;
		for(int i=0; i<eggs.length; i++) {
			if(i == idx) continue;
			
			if(eggs[i].durability > 0 && eggs[idx].durability > 0) {
				eggs[i].durability -= eggs[idx].weight;
				eggs[idx].durability -= eggs[i].weight;
				
				pickEgg(idx+1, eggs);
				flag=true;
				
				eggs[i].durability += eggs[idx].weight;
				eggs[idx].durability += eggs[i].weight;				
			}
			else pickEgg(idx+1, eggs);
		}
		
		
	}

	private static int findNext(int idx, Egg[] eggs) {
		for(int j=idx; j<eggs.length; j++) {	
			if(eggs[j].durability > 0) {
				return j;
			}
		}
		return eggs.length;
	}


	private static void print(Egg[] eggs) {
		for(int i=0; i<eggs.length; i++) {
			System.out.println(eggs[i].toString());
		}	
		System.out.println();
	}



	private static void hitEgg(Egg[] eggs) {
		Queue<Egg[]> q = new LinkedList<>();
		q.add(eggs);
		int idx = 0;
		
		while(!q.isEmpty()) {
			Egg[] curEgg = q.poll();
			
			System.out.println(idx+" q size:"+q.size());
			print(curEgg);
			System.out.println("위는 q.poll임");
			
			if(idx >= eggs.length) {
				int cnt = 0;
				
				for(int i=0; i<curEgg.length; i++) {
					if(curEgg[i].durability <= 0) cnt++;
				}
				
				Ans = Math.max(Ans, cnt);
				
				continue;
			}
			
			for(int i=0; i<curEgg.length; i++) {
				if(i == idx) continue;
				Egg[] newEgg = null;
				
				if(curEgg[idx].durability > 0) {
					curEgg[i].durability -= curEgg[idx].weight;
					curEgg[idx].durability -= curEgg[i].weight;
					newEgg = copyValue(curEgg);
					curEgg[i].durability += curEgg[idx].weight;
					curEgg[idx].durability += curEgg[i].weight;
				} else {
					newEgg = copyValue(curEgg);
				}
				
				System.out.println("q에 추가되는 에그");
				print(newEgg);
				q.add(newEgg);
			}
			
			idx++;
		}
		
	}


	private static Egg[] copyValue(Egg[] curEgg) {
		Egg[] newEgg = new Egg[curEgg.length];
		
		for(int k=0; k<curEgg.length; k++) {
			newEgg[k] = new Egg(curEgg[k].durability, curEgg[k].weight);
		}
		
		return newEgg;
	}
}
