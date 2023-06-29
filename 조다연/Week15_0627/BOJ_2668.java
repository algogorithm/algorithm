package week15_0627;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_2668 {
	//숫자고르기
	static int N;
	static int[] arr;
	static boolean[] v;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		v = new boolean[N+1];
		list = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
			
		for(int i=1; i<=N; i++) {
			if(!v[i]) {
				v[i] = true;
				//사이클 발생하는지
				dfs(i, i);
				v[i] = false;
			}
		}
		
		Collections.sort(list);
		System.out.println(list.size());
		for(int n : list) System.out.println(n);
	}


	private static void dfs(int i, int start) {
		//시작했던 숫자와 같아지면 (사이클) 
		//해당 숫자 넣어주기
		if(arr[i] == start) list.add(start);
		
		if(!v[arr[i]]) {
			v[arr[i]] = true;
			dfs(arr[i], start);
			v[arr[i]] = false;
		}
		
	}

}
