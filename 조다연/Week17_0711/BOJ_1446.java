package week17_0711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1446 {
	//지름길
	static ArrayList<Point>[] path; //지름길 정보 저장 리스트

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//0부터 시작해서 D에 도착
		int N = Integer.parseInt(st.nextToken()); //지름길 개수
		int D = Integer.parseInt(st.nextToken()); // 고속도로 길이
		
		int[] distance = new int[D+1];
		path = new ArrayList[10001]; //d의 범위 10000보다 작거나 같음
		
		Arrays.fill(distance, Integer.MAX_VALUE); //거리배열 최댓값으로 초기화
		for(int i=0; i<10001; i++) {
			path[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()); //시작위치
			int e = Integer.parseInt(st.nextToken()); //도착위치
			int dist = Integer.parseInt(st.nextToken()); //지름길 길이
			
			//지름길이 무조건으로 최단 거리는 아님 -> 지름길일 경우만 저장
			if(e-s > dist) {
				path[e].add(new Point(s,dist));
			}
		}
		
		//최단거리 구하기
		distance[0] = 0; //0->0까지 이동거리 0
		for(int i=1; i<=D; i++) {
			if(path[i].size()>0) {
				//i지점에 도착하는 지름길이 있다면 
				//지름길 중 가장 최단 거리로 갱신
				for(Point p : path[i]) {
					//이미 갱신되었다면
					if(distance[p.start]+p.dist > distance[i]) continue;
					
					distance[i] = Math.min(distance[i-1]+1, distance[p.start]+p.dist);
				}
				continue;
			}
			distance[i] = distance[i-1]+1;
		}
		
		System.out.println(distance[D]);
	}
	
	static class Point {
		int start;
		int dist;
		
		Point(int start, int dist) {
			this.start = start;
			this.dist = dist;
		}
	}

}
