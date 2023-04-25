package BaekJoon;

import java.io.*;

public class B_2447 {
	static char [][] arr;
	
	public static void star(int n, int start1, int start2) {
		for(int i=start1; i<n+start1; i=i+n/3) {
			for(int j=start2; j<n+start2; j=j+n/3) {
				if(i==start1+n/3 && j==start2+n/3) {
					blank(n,i,j);
					continue;
				}
				
				if(n/3>1)	star(n/3,i,j);
				else	arr[i][j] = '*';
			}
		}
	}
	
	public static void blank(int n, int start1, int start2) {
		for(int i=start1; i<n/3+start1; i++) {
			for(int j=start2; j<n/3+start2; j++) {
				arr[i][j] = ' ';
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		arr = new char[N][N];
		
		star(N,0,0);
		
		for(int i=0; i<N; i++) {
			bw.write(arr[i]);
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}