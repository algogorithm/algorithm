package week20_0801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10844 {
	//���� ��� ��
	static int N;
    static long mod = 1000000000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		long dp[][] = new long[N+1][10];
		
		/* ù��° �ڸ����� ����� ���� �ϳ� ���� */
        for(int i=1; i<10; i++) {
            dp[1][i] = 1;
        }
        
        /* �ι�° �ڸ������� N��° �ڸ������� Ž�� */
        for(int i=2; i<=N; i++) {
            /* ���� �ڸ����� 0���� 9���� Ž��*/
            for(int j=0; j<10; j++) {
                // �ڸ����� 9��� ���� �ڸ����� 8�� ����
                if(j == 9) {    
                    dp[i][9] = dp[i-1][8]%mod;
                }
                // �ڸ����� 0�̶�� ���� �ڸ����� 1�� ����
                else if(j==0) {    
                    dp[i][0] = dp[i-1][1] % mod;
                }
                // �� �ܴ� ���� �ڸ����� -1, +1 ����
                else {    
                    dp[i][j] = (dp[i-1][j-1]+ dp[i-1][j+1])%mod;
                }
            }
        }
        
        long ans = 0;
        for(int i=0; i<10; i++) {
            ans += dp[N][i];
        }
        
        System.out.println(ans%mod);
	}

}
