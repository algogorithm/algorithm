package BaekJoon;
// 코드 참고 : https://dundung.tistory.com/60
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class B_1722 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int A[] = new int[N];
		boolean V[] = new boolean[N+1]; //순서를 지켜야 하므로 이미 추가한 수는 체크
		long F[] = new long[N+1];
		
		Arrays.fill(F, 1);
		for(int i=1; i<=N; i++) { // N개의 순열의 개수는 N!개
			F[i] = F[i-1]*i;
		}
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		
		if(P == 1) {
			long K = Long.parseLong(st.nextToken());
			for(int i=0; i<N; i++) {
				for(int j=1; j<=N; j++) {
					if(V[j])	continue; // 이미 채워진 수라면 갯수 안더함
					if(F[N-i-1] < K)	K -= F[N-i-1]; // 작은 순 이므로 현재의 숫자의 순열 갯수보다 더 크면 다음 숫자가 더 앞
					else { // 현재의 숫자의 순열 갯수보다 작으면 지금 숫자 현재의 자릿수이고, 작은 순이므로 다시 처음부터 탐색
						A[i] = j;
						V[j] = true;
						break;
					}
				}
			}
			
			for(int i : A) {
				sb.append(i).append(" ");
			}
		} else {
			long ans = 1;			
			for(int i=0; i<N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<N; i++) {
				for(int j=1; j<A[i]; j++) { // 현재의 숫자보다 더 작은 수들 중 아직 추가 안된 수는 그 자리의 순열의 갯수 만큼 더하기
					if(!V[j])	ans += F[N-i-1];
				}
				V[A[i]] = true;
			}
			
			sb.append(ans);
		}
		
		System.out.print(sb);
	}
}