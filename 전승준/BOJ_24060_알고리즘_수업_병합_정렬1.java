import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24060_알고리즘_수업_병합_정렬1 {
	static int N, K, count, res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<A.length; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		merge_sort(A, 0, A.length-1);
		
		for(int i=0; i<A.length; i++) {
			System.out.print(A[i] + " ");
		}
		
		System.out.println(count <= K ? count : -1);
	}

	private static void merge_sort(int[] A, int p, int r) {
		if(p < r) {
			int q = (p+r) / 2;
			merge_sort(A, p, q);
			merge_sort(A, q+1, r);
			merge(A, p, q, r);
		}
	}

	private static void merge(int[] A, int p, int q, int r) {
		int i=p, j=q+1, t=1;
		int[] tmp = new int[A.length];
		
		while(i<=q && j<=r) {
			if(A[i] <= A[j]) {
				tmp[t++] = A[i++];
			} else {
				tmp[t++] = A[j++];
			}
		}
		while(i<=q) {
			tmp[t++] = A[i++];
		}
		while(j<=r) {
			tmp[t++] = A[j++];
		}
		i=p;
		t=1;
		while(i<=r) {
			A[i++] = tmp[t++];
		}
		
	}

}