package week4_0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16987 {
	//16987. 계란으로 계란치기

	static int N,ans=0;
	static Egg[] eggs;
	
	static class Egg {
		int d; // 내구도 
		int w;// 무게
		boolean s; //깨진 상태
		
		public Egg(int d, int w, boolean s) {
			super();
			this.d = d;
			this.w = w;
			this.s = s;
		}		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//계란의 수 N
		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N];
		 
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
	
			eggs[i] = new Egg(d,w,true); 
		}
		
		breakEgg(0,0);
		
		System.out.println(ans);
	}
	
	private static void breakEgg(int now, int cnt) {
		//마지막 계란까지 봤으면 종료
		if(now == N) {
			ans = Math.max(ans, cnt);
			return;
		}

		//손에 든 계란이 깨져 있거나 나머지 계란이 깨져 있다면 다음 계란으로
		if(!eggs[now].s || cnt == N-1) {
			breakEgg(now+1, cnt);
			return;
		}

		//계란 부딪히기
		int tmp = cnt;
		for(int i=0; i<N; i++) {
			//손에 들고 있는 계란과 같으면 넘어가
			if(now == i) continue;
			
			//깨진 계란이면 넘어가
			if(!eggs[i].s) continue;
			
			//계란 깨기(내구도 - 무게)
			eggs[now].d -= eggs[i].w;
			eggs[i].d -= eggs[now].w;
			
			//깨진 쪽 상태 바꾸고 cnt++
			if(eggs[now].d<=0) {
				eggs[now].s = false;
				cnt++;
			}
			if(eggs[i].d<=0) {
				eggs[i].s = false;
				cnt++;
			}
			
			//다음 계란으로
			breakEgg(now+1, cnt);
			
			//원상복구
			eggs[now].d += eggs[i].w;
			eggs[now].s = true;
			eggs[i].d += eggs[now].w;
			eggs[i].s = true;
			cnt = tmp;
		}	
	}
}
