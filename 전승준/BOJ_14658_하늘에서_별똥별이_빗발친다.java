import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14658_하늘에서_별똥별이_빗발친다 {
	static class ShootingStar {
		int r;
		int c;
		
		ShootingStar(int r, int c){
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "ShootingStar [r=" + r + ", c=" + c + "]";
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken()); // 트럼펠린 크기(l x l)
		int k = Integer.parseInt(st.nextToken()); // 별똥별 수
		
		List<ShootingStar> stars = new ArrayList<>();

		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			stars.add(new ShootingStar(r, c)); // 별똥별 입력
		}
		
		if(n <= l && m <= l) {
			System.out.println(0);
			return;
		}
		
		int ans = 0, count = 0;
		
		for(int s1=0; s1<stars.size(); s1++) {
			for(int s2=0; s2<stars.size(); s2++) {
				ShootingStar[] star = { stars.get(s1), stars.get(s2) };
				
				for(int d=0; d<2; d++) {
					int sx = star[d].r;
					int sy = star[1-d].c;
					count = 0;
					
					for(ShootingStar chk : stars) {
						if(chk.r>=sx && chk.r<=sx+l && chk.c>=sy && chk.c<=sy+l) {
							count++;
						}
					}
					ans = Math.max(ans, count);
				}
				
			}
		}
		
		System.out.println(k-ans);
	}

}