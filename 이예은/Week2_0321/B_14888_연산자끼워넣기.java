package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14888 {
	static int n[], min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	
	public static void cal(int d[], int p[], int idx) {
		if(idx == n.length-1) {
			int a = n[0];
			for(int i=0; i<idx; i++) {
				if(d[i] == 0)	a += n[i+1];
				else if(d[i] == 1)	a -= n[i+1];
				else if(d[i] == 2) a *= n[i+1];
				else	{
					if(a < 0)	a = -(Math.abs(a) / n[i+1]);
					else	a /= n[i+1];
				}
			}
			
			min = Math.min(a, min);
			max = Math.max(a, max);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(p[i] != 0) {
				d[idx] = i;
				p[i]--;
				cal(d, p, idx+1);
				p[i]++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = new int[N];
		int p[] = new int[4];
		
		for(int i=0; i<N; i++)
			n[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int j=0; j<4; j++)
			p[j] = Integer.parseInt(st.nextToken());
		
		cal(new int[n.length-1], p, 0);
		
		System.out.print(max+"\n"+min);
	}
}