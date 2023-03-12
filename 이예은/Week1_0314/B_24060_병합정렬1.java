package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_24060 {
	static int N, K, ans = -1, n, array[], tmp[];
	
	public static void merge_sort(int p, int r) {
		if(n >= K)	return;
		if(p < r) {
			int q = (p+r)/2;
			merge_sort(p, q);
			merge_sort(q+1, r);
			merge(p, q, r);
		}
	}
	
	public static void merge(int p, int q, int r) {
		int i = p, j = q+1, t = 0;
		
		while(i <= q && j <= r) {
			if(array[i] <= array[j])	tmp[t++] = array[i++];
			else	tmp[t++] = array[j++];
		}
		
		while(i <= q)	tmp[t++] = array[i++];
		while(j <= r)	tmp[t++] = array[j++];
		
		i = p; t = 0;
		while(i <= r) {
			if(++n == K) {
				ans = tmp[t];
				break;
			}
			array[i++] = tmp[t++];
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		array = new int[N];
		tmp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		merge_sort(0, array.length-1);
		
		System.out.print(ans);
	}
}