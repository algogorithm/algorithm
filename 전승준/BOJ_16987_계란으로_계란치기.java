import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16987_계란으로_계란치기 {
	static class Egg{
		int durability;
		int weight;
		boolean isDead;
		
		Egg(int durability, int weight){
			this.durability = durability;
			this.weight = weight;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		Egg[] eggs = new Egg[T];
		
		for(int i=0; i<T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), 
							  Integer.parseInt(st.nextToken()));
		}
		
		for(int i=0; i<T; i++) {
			Egg pick = eggs[i];
			
			
		}

	}

}
